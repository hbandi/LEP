package com.hb.db.event;

import com.hb.model.CriteriaEventActivity;
import com.hb.query.criteria.types.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ActivityDAO {

    private static String ACTIVITY_DATA_COLLECTION = "activity";

    private Logger log = LoggerFactory.getLogger(ActivityDAO.class.getSimpleName());
    @Autowired
    private MongoTemplate mongoTemplet;


    public void saveActivity(CriteriaEventActivity activity) {
        log.debug("ActivityDAO.saveActivity has been called for activity {} ", activity);
        try {
            mongoTemplet.save(activity, ACTIVITY_DATA_COLLECTION);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Fnd An EXPN while storing event Meta Data {} ", ex.getMessage());
        }
    }

    /**
     * private String criteriaId;
     * private CriteriaType criteriaType;
     *
     * @param eventIdentifierKey
     * @param criteriaType
     * @param criteriaId
     * @return
     */

    public Criteria getAllEventCriteriasByCriteriaIdentifierKey(String eventIdentifierKey, String criteriaType, long criteriaId) {
        log.debug("ActivityDAO.getAllEventCriteriasByCriteriaIdentifierKey has been called,loading from DB ");
        Criteria applicableCriteria = null;
        try {
            org.springframework.data.mongodb.core.query.Criteria queryCriteria =
                    org.springframework.data.mongodb.core.query.Criteria.where("eventIdentifierKey").
                            is(eventIdentifierKey).and("criteriaType").is(criteriaType.toString()).and("criteriaId").is(criteriaId);
            Query query = new Query();
            query.addCriteria(queryCriteria);
            applicableCriteria = (Criteria) mongoTemplet.find(query, Criteria.class, ACTIVITY_DATA_COLLECTION);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Fnd An EXPN while loading event Meta Data {} ", ex.getMessage());
        }
        return applicableCriteria;
    }

    public void updateCriteria(CriteriaEventActivity updateAtcivity) {
        log.debug("ActivityDAO.updateCriteriasByCriteriaIdentifierKey has been called,Trying to update::  ");
        List<Criteria> applicableCriteriaList = null;
        try {
            org.springframework.data.mongodb.core.query.Criteria queryCriteria =
                    org.springframework.data.mongodb.core.query.Criteria.where("eventIdentifierKey").
                            is(updateAtcivity.getEventIdentifierKey()).and("criteriaType").is(updateAtcivity.getCriteria().getCriteriaType().toString()).and("criteriaId").is(updateAtcivity.getCriteriaId());
            Query query = new Query();
            query.addCriteria(queryCriteria);
            CriteriaEventActivity foundCriteria = mongoTemplet.findOne(query, CriteriaEventActivity.class, ACTIVITY_DATA_COLLECTION);
            foundCriteria.setCriteria(updateAtcivity.getCriteria());
            this.saveActivity(foundCriteria);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Fnd An EXPN while loading event Meta Data {} ", ex.getMessage());
        }
    }

}
