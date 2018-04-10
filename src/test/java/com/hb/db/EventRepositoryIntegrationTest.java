package com.hb.db;

import com.hb.db.event.EventMetaDataDAO;
import com.hb.db.event.MongoConfig;
import com.hb.model.EventValaMetaData;
import com.hb.query.spel.filter.SpelFilterQueryContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class EventRepositoryIntegrationTest {

    @Autowired
    private EventMetaDataDAO eventMetaDataDAO;


    @Autowired
    private SpelFilterQueryContext spelFilterQueryContext;


    @Test
    public void whenSavingEvent_thenAvailableOnRetrieval() throws Exception {
        String id = String.valueOf(System.currentTimeMillis());
        List<String> l = new ArrayList<>();
        l.add("CUSTOMER_REGISTRATION_EVENT_TEST");
        EventValaMetaData event = new EventValaMetaData();
        event.setApplicableFishTypes(l);
        event.setFishNetFilter(spelFilterQueryContext.startComplexExpression().eq("customerName", "HB").endComplexExpression().getQuery());
        event.setUniqueIdentifierForEvent(String.valueOf(System.currentTimeMillis()));

        eventMetaDataDAO.save(event);

        // System.out.println("found event is :: "+event1);
    }


}