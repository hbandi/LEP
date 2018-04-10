package com.hb.db.event;

import com.hb.model.EventValaMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This repository will basically stores the event meta data.
 */

@Component
public class EventMetaDataDAO {

    private static String META_DATA_COLLECTION = "eventValaMetaData";

    private Logger log = LoggerFactory.getLogger(EventMetaDataDAO.class.getSimpleName());
    @Autowired
    private MongoTemplate mongoTemplet;


    public void save(EventValaMetaData eventValaMetaData) {
        log.debug("EventMetaDataDAO.save has been called for metaData " + eventValaMetaData);
        try {
            mongoTemplet.save(eventValaMetaData);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Fnd An EXPN while storing event Meta Data {} ", ex.getMessage());
        }
    }

    public List<EventValaMetaData> loadAllEventMetaData() {
        log.debug("EventMetaDataDAO.loadAllEventMetaData has been called,loading from DB ");
        List<EventValaMetaData> metaDataList = null;
        try {
            metaDataList = (List<EventValaMetaData>) mongoTemplet.findAll(EventValaMetaData.class, META_DATA_COLLECTION);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Fnd An EXPN while loading event Meta Data {} ", ex.getMessage());
        }
        return metaDataList;
    }
}
