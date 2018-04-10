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

@Component
@Order(2)
@Scope("prototype")
public class ValaMetaDataEventActivity<T extends Context> implements Activity<Context> {

    @Autowired
    private MetaDataEventHandler metaDataEventHandler;

    private Logger log = LoggerFactory.getLogger(EventMetaDataCache.class.getSimpleName());

    @Override
    public Context process(Context context) {
        log.debug("ValaMetaDataEventActivity start");
        if (context.getEventType().equalsIgnoreCase("EVENT_METADATA")) {
            metaDataEventHandler.handleEvent(context);
            context.setProcessNeedsStop(true);
        }
        return context;
    }
}
