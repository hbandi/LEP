package com.hb.engine;

import com.hb.engine.activity.*;
import com.hb.engine.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Service
@ComponentScan("com.hd.engine")
public class EventValaActivityProcessFlow/*<T extends Activity>*/ {

    private Logger log = LoggerFactory.getLogger(EventValaActivityProcessFlow.class.getSimpleName());

    @Autowired
    private List<Activity> activities=new ArrayList<>();
    @Autowired
    private ConsumerActivity consumerActivity;
    @Autowired
    private ValaMetaDataEventActivity  valaMetaDataEventActivity;
    @Autowired
    private FilterActivity filterActivity;
    @Autowired
    private ApplyCriteriaActivity applyCriteriaActivity;
    @Autowired
    private PostProcessorActivity  postProcessorActivity;


    public void process(Context context) {
        activities.add(consumerActivity);
        activities.add(valaMetaDataEventActivity);
        activities.add(filterActivity);
        activities.add(applyCriteriaActivity);
        activities.add(postProcessorActivity);
        long startTime = System.currentTimeMillis();
        long processTime;
        log.debug("EventValaActivityProcessFlow.process been  called for Context {} ", context);
        try {
            for (Activity activity : activities) {
                try {
                    long activityStartTime = System.currentTimeMillis();
                    activity.process(context);
                    log.debug("EventValaActivityProcessFlow.{} Activity Took in ms  {} ", activity.getActivityName(), (System.currentTimeMillis() - activityStartTime));
                } catch (Throwable th) {
                    break;
                }
                if (context.isProcessNeedsStop()) {
                    break;
                }
            }
        } catch (Exception ex) {
            log.error("Found EXPN while processing context {} ", context);
            ex.printStackTrace();
        }
        processTime = System.currentTimeMillis() - startTime;
        log.debug(" EventValaActivityProcessFlow.process ,Total Time took as in ms {} ", processTime);
    }

}
