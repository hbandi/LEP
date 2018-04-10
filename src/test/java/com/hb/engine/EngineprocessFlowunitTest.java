package com.hb.engine;


import com.hb.Customer;
import com.hb.db.event.MongoConfig;
import com.hb.engine.context.Context;
import com.hb.model.EventValaMetaData;
import com.hb.query.spel.filter.SpelFilterQueryContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
@SpringBootTest
@Component
public class EngineprocessFlowunitTest extends AbstractJUnit4SpringContextTests {


    private static ClassPathXmlApplicationContext appContext;
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private SpelFilterQueryContext spelFilterQueryContext;



    @BeforeClass
    public static void init() {
        appContext = new ClassPathXmlApplicationContext("META-INF/event-config-bean-context.xml");
    }

    @Test
    public void when_call_processflow_check_theLogs() {
        Context context = new Context();
        context.setEventType("CUSTOMER_REG_EVENT");
        EventValaActivityProcessFlow f = (EventValaActivityProcessFlow) appContext.getBean("fishNetActivityProcessFlow");
        f.process(context);
    }

    @Test
    public void when_call_processflow_first_insertion() {
        Context context = new Context();
        context.setEventType("CUSTOMER_REG_EVENT");
        List<EventValaMetaData>  metaDataList=new ArrayList<>();
        EventValaMetaData mdata=new EventValaMetaData();
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").and().lt("age", "18").and().ge("pruchaseAmount","80").endComplexExpression().getQuery();
        mdata.setFishNetFilter(spel);
        metaDataList.add(mdata);
        context.setFilteredEventsMetaData(metaDataList);

        Customer cm=new Customer("user389", 121, 100, "HYD", 17);
        context.setInEvent(cm);

        EventValaActivityProcessFlow f = new EventValaActivityProcessFlow();
        f.process(context);
    }



    @Test
    public void when_trigger_metaDataEvent() {
        Context context = new Context();
        context.setEventType("EVENT_METADATA");
        EventValaActivityProcessFlow f = (EventValaActivityProcessFlow) appContext.getBean("fishNetActivityProcessFlow");
        f.process(context);
    }


}
