package com.hb.engine.activity;

import com.hb.cache.EventMetaDataCache;
import com.hb.engine.context.Context;
import com.hb.engine.handlers.MetaDataEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * event Type configuration
 * eventType === Customer_Registration_event
 * eventUnique Identifier === "customerName"
 */
@Component
@Order(1)
@Scope("prototype")
public class ConsumerActivity<T extends Context> implements Activity<Context> {

    private Logger log = LoggerFactory.getLogger(EventMetaDataCache.class.getSimpleName());


    @Override
    public Context process(Context context) {

        try {
            log.debug("ConsumerActivity start ,{}", context);

            if (context.getEventType() == null && context.getEventType().isEmpty()) {
                context.setProcessNeedsStop(true);
                log.debug("ConsumerActivity:: eventType is NULL,So Return");
                return context;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            log.error("Found EXPN in ConsumerActivity ");
        }

        return context;
    }

}
