package com.hb.model;

import com.hb.query.criteria.CriteriaType;
import com.hb.query.criteria.types.Criteria;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class CriteriaEventActivity implements Serializable {

    @Id
    private String activityId;
    private String criteriaId;


    /**
     * eventIdentifierKey
     * is Nothing but key for which the crteria got MET,Like customerName is like "hbandi",userName "user2340" etc.
     */
    private String eventIdentifierKey;
    private Criteria criteria;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(String criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getEventIdentifierKey() {
        return eventIdentifierKey;
    }

    public void setEventIdentifierKey(String eventIdentifierKey) {
        this.eventIdentifierKey = eventIdentifierKey;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
