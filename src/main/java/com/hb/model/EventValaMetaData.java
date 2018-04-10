package com.hb.model;

import com.hb.query.criteria.types.Criteria;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class EventValaMetaData implements Serializable {

    /**
     * Id is auto generated,reference to find unique fish net
     */
    @Id
    private String fishNetId;
    /**
     * fishNetDescription contains description about fishnet.
     */
    private String fishNetDescription;
    /**
     * lis of applicableFishType should tell us which fishes/events we are looking for
     * nothing but,which types of events we are looking to filter.
     * Ex. CUSTOMER_REGISTRATION_EVENT
     */
    private List<String> applicableFishTypes;


    /**
     * fishNetFilter is filter,which is nothing but fish net(hole size or shape etc)
     * which is used for finding events/fishes.
     */

    private String fishNetFilter;

    /**
     * fishProcessCriteria is list of criteria which is required to evaluate against events.
     */
    private List<Criteria> valaProcessCriterias;

    /**
     * if fishProcessCriteria contains more than one element,
     * one has to set any one of these values
     * by default it is isOr==true;
     * if any one wants to set isAnd =true means ,all the criteria has to be met.
     * if any one wants to set isAnd =false means ,isOr=true.
     */
    private boolean isOR;
    private boolean isAnd;

    /**
     * uniqueIdentifierForEvent is nothing but ,on which attribute we need to evaluate.
     * Like in customer Object,customerName has to be set.
     * for PurchaseOrder Event ,purchaseId has to be set for this field.(not value ,its a name of attribute)
     */
    private String uniqueIdentifierForEvent;

    private Map<String, Object> additionalEventMetaData;


    public List<Criteria> getValaProcessCriterias() {
        return valaProcessCriterias;
    }

    public void setValaProcessCriterias(List<Criteria> valaProcessCriterias) {
        this.valaProcessCriterias = valaProcessCriterias;
    }

    @Override
    public String toString() {
        return "EventValaMetaData{" +
                "fishNetId='" + fishNetId + '\'' +
                ", fishNetDescription='" + fishNetDescription + '\'' +
                ", applicableFishTypes=" + applicableFishTypes +
                ", fishNetFilter='" + fishNetFilter + '\'' +
                ", valaProcessCriterias=" + valaProcessCriterias +
                ", isOR=" + isOR +
                ", isAnd=" + isAnd +
                ", uniqueIdentifierForEvent='" + uniqueIdentifierForEvent + '\'' +
                ", additionalEventMetaData=" + additionalEventMetaData +
                '}';
    }


    public String getFishNetId() {
        return fishNetId;
    }

    public void setFishNetId(String fishNetId) {
        this.fishNetId = fishNetId;
    }

    public String getFishNetDescription() {
        return fishNetDescription;
    }

    public void setFishNetDescription(String fishNetDescription) {
        this.fishNetDescription = fishNetDescription;
    }

    public List<String> getApplicableFishTypes() {
        return applicableFishTypes;
    }

    public void setApplicableFishTypes(List<String> applicableFishTypes) {
        this.applicableFishTypes = applicableFishTypes;
    }

    public String getFishNetFilter() {
        return fishNetFilter;
    }

    public void setFishNetFilter(String fishNetFilter) {
        this.fishNetFilter = fishNetFilter;
    }


    public boolean isOR() {
        return isOR;
    }

    public void setOR(boolean OR) {
        isOR = OR;
    }

    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd(boolean and) {
        isAnd = and;
    }

    public String getUniqueIdentifierForEvent() {
        return uniqueIdentifierForEvent;
    }

    public void setUniqueIdentifierForEvent(String uniqueIdentifierForEvent) {
        this.uniqueIdentifierForEvent = uniqueIdentifierForEvent;
    }

    public Map<String, Object> getAdditionalEventMetaData() {
        return additionalEventMetaData;
    }

    public void setAdditionalEventMetaData(Map<String, Object> additionalEventMetaData) {
        this.additionalEventMetaData = additionalEventMetaData;
    }
}
