package com.hb.engine.activity;


import com.hb.cache.EventMetaDataCache;
import com.hb.engine.context.Context;
import com.hb.model.EventValaMetaData;
import com.hb.query.spel.filter.SpelFilterQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(3)
@Scope("prototype")
public class FilterActivity<T extends Context> implements Activity<Context> {

    private Logger log = LoggerFactory.getLogger(ApplyCriteriaActivity.class.getSimpleName());

    @Autowired
    private SpelFilterQuery spelFilterQuery;

    @Autowired
    private EventMetaDataCache eventMetaDataCache;

    @Override
    public Context process(Context context) {

        log.debug(" FilterActivity Start ");
        try {
            List<EventValaMetaData> evntTypeMappedMetaData = (List<EventValaMetaData>) eventMetaDataCache.getEventTypeVsMetaData().
                    get(context.getEventType());
            log.debug(" FilterActivity.process :: mapped vala data for the evntType :: {}", evntTypeMappedMetaData);
            List<EventValaMetaData> filteredList = evntTypeMappedMetaData.stream().filter(metaData -> {
                return spelFilterQuery.eval(metaData.getFishNetFilter(), context.getInEvent());
            }).collect(Collectors.toList());
            log.debug(" FilterActivity inEvent satisfied Event filters list {} ", filteredList);
            if (filteredList == null || filteredList.size() == 0) {
                log.debug(" FilterActivity inEvent satisfied Event filters list size is null,so exit from activity::: ");
                context.setProcessNeedsStop(true);
                return context;
            }
            context.setFilteredEventsMetaData(filteredList);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Found exception in FilterActivity:process {}", ex.getMessage());
        }
        return context;
    }
}
