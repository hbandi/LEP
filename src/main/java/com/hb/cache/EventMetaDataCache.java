package com.hb.cache;

import com.hb.db.event.EventMetaDataDAO;
import com.hb.engine.util.FixedSizeConcurrentMap;
import com.hb.model.EventValaMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class EventMetaDataCache<T extends EventValaMetaData> {

    public FixedSizeConcurrentMap<String, List<? super EventValaMetaData>> eventTypeVsMetaData;

    private Logger log = LoggerFactory.getLogger(EventMetaDataCache.class.getSimpleName());
    @Autowired
    private EventMetaDataDAO eventMetaDataDAO;

    @PostConstruct
    public void init() {
        log.debug("initilizing EventMetaDataCache :: with fixed size concurentMap ");
        eventTypeVsMetaData = new FixedSizeConcurrentMap<>();
        try {
            initMetaDataCache();
            log.debug("Loaded EventMetaDataCache FSM Map :: {} ", eventTypeVsMetaData);
        } catch (Exception ex) {
            log.error(" Found EXPN in EventMetaDataCache.init,while initilizing eventTypeVsMD cache.");
        }
    }

    public void initMetaDataCache() throws Exception {
        final List<EventValaMetaData> metaDataList = eventMetaDataDAO.loadAllEventMetaData();
        if (metaDataList != null && metaDataList.size() > 0) {
            log.debug("initMetaDataCache MetaData List Loaded From DB,doing transformation.");
            Map<String, List<EventValaMetaData>> transformedMap = generateEventTypeVsMetaDataMap(metaDataList);
            if (transformedMap != null && transformedMap.size() > 0) {
                transformedMap.forEach((k, v) -> eventTypeVsMetaData.put(k, v));
            }
        }
        log.debug("initMetaDataCache with Transformed Map and loaded fixed size Map :: {} ", eventTypeVsMetaData);

    }

    public Map<String, List<EventValaMetaData>> generateEventTypeVsMetaDataMap(List<EventValaMetaData> metaDataList) throws Exception {
        return metaDataList.stream().flatMap(metaData -> metaData.getApplicableFishTypes().stream().
                map(type -> new AbstractMap.SimpleEntry<>(type, metaData))).collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

    }

    public void populateEventTypeVsMdataOnRealTime(String eventType, EventValaMetaData metaData) {
        log.debug("EventMetaDataCache:populateEventTypeVsMdataOnRealTime cled with eventType :: {} ", eventType);
        try {
            List<? super EventValaMetaData> valueList = null;
            if (eventTypeVsMetaData.contains(eventType)) {
                valueList = eventTypeVsMetaData.get(eventType);
                valueList.add(metaData);
                eventTypeVsMetaData.put(eventType, valueList);
            } else {
                valueList = new ArrayList<>();
                valueList.add(metaData);
                eventTypeVsMetaData.put(eventType, valueList);
            }
        } catch (Exception ex) {
            log.error(" Found EXPN in EventMetaDataCache.populateEventTypeVsMdataOnRealTime,while initilizing eventTypeVsMD cache.");
        }
    }

    public FixedSizeConcurrentMap<String, List<? super EventValaMetaData>> getEventTypeVsMetaData() {
        return eventTypeVsMetaData;
    }

    public void setEventTypeVsMetaData(FixedSizeConcurrentMap<String, List<? super EventValaMetaData>> eventTypeVsMetaData) {
        this.eventTypeVsMetaData = eventTypeVsMetaData;
    }

}
