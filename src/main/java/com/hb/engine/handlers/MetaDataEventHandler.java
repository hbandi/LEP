package com.hb.engine.handlers;

import com.hb.cache.EventMetaDataCache;
import com.hb.db.event.EventMetaDataDAO;
import com.hb.engine.context.Context;
import com.hb.model.EventValaMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@ComponentScan("com.hb")
@Component
public class MetaDataEventHandler<T extends Context> implements EventHandler<Context> {

    private Logger log = LoggerFactory.getLogger(MetaDataEventHandler.class.getSimpleName());

    @Autowired
    private EventMetaDataCache eventMetaDataCache;

    @Autowired
    private EventMetaDataDAO eventMetaDataDAO;

    @Override
    public Context handleEvent(Context context) {
        log.debug(" MetaDataEventHandler.handleEvent handling REALTime MetaData Event :: {} ",(EventValaMetaData) context.getInEvent());
        try {
            eventMetaDataCache.populateEventTypeVsMdataOnRealTime(context.getEventType(), (EventValaMetaData) context.getInEvent());
            eventMetaDataDAO.save((EventValaMetaData) context.getInEvent());
            System.out.println("in the handler :: ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return context;
    }
}
