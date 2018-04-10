package com.hb.db;


import com.hb.db.event.MongoConfig;
import com.hb.model.EventValaMetaData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class, loader = AnnotationConfigContextLoader.class)
public class EventMetaDataCacheTest {


    @Test
    public void afterSavingEventMetaData_thenLoadAllEventsMeataData() throws Exception {
        //  Map<String, List<EventValaMetaData>> metaDataListMap = eventMetaDataCache.getEventTypeVsMetaData();
        // System.out.println("Meta data List in test case::  " + metaDataListMap);
        //System.out.println("Size :: " + metaDataListMap.size());
    }

    @Test
    public void validate_LoadLgic_when_dataGot_from_db() {

        EventValaMetaData f1 = new EventValaMetaData();
        f1.setUniqueIdentifierForEvent("customerName");
        f1.setFishNetFilter("filter1");
        f1.setApplicableFishTypes(Stream.of("EVENT_1", "EVENT_2", "EVENT_3").collect(Collectors.toList()));
        f1.setFishNetId("1");


        EventValaMetaData f2 = new EventValaMetaData();
        f2.setUniqueIdentifierForEvent("customerName");
        f2.setFishNetFilter("filter2");
        f2.setApplicableFishTypes(Stream.of("EVENT_1", "EVENT_4", "EVENT_2").collect(Collectors.toList()));
        f2.setFishNetId("2");

        EventValaMetaData f3 = new EventValaMetaData();
        f3.setUniqueIdentifierForEvent("customerName");
        f3.setFishNetFilter("filter2");
        f3.setApplicableFishTypes(Stream.of("EVENT_2", "EVENT_4", "EVENT_3").collect(Collectors.toList()));
        f3.setFishNetId("3");
        List<EventValaMetaData> metaDataList = Stream.of(f1, f2, f3).collect(Collectors.toList());

        Map<String,List<EventValaMetaData>> map=metaDataList.stream().flatMap(metaData -> metaData.getApplicableFishTypes().stream().
                map(type -> new AbstractMap.SimpleEntry<>(type, metaData))).collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        Assert.assertEquals(map.size(),4);
        Assert.assertEquals(map.containsKey("EVENT_3"),true);
        Assert.assertEquals(map.containsKey("EVENT_5"),false);
    }


}
