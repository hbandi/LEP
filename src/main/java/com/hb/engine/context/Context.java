package com.hb.engine.context;

import com.hb.model.EventValaMetaData;
import com.hb.query.criteria.types.Criteria;

import java.util.List;

public class Context<T, U extends EventValaMetaData> {

    private T inEvent;
    private U eligibleEventsMetaData;
    private String eventType;
    /**
     * CustomerName or any identifier which you want to listen.
     */
    private String eventUniqueIdentifier;

    private boolean isProcessNeedsStop;

    private List<EventValaMetaData> filteredEventsMetaData;


    private List<Criteria> mappedCriterias;


    public String getEventUniqueIdentifier() {
        return eventUniqueIdentifier;
    }

    public void setEventUniqueIdentifier(String eventUniqueIdentifier) {
        this.eventUniqueIdentifier = eventUniqueIdentifier;
    }


    public List<EventValaMetaData> getFilteredEventsMetaData() {
        return filteredEventsMetaData;
    }

    public void setFilteredEventsMetaData(List<EventValaMetaData> filteredEventsMetaData) {
        this.filteredEventsMetaData = filteredEventsMetaData;
    }

    @Override
    public String toString() {
        return "Context{" +
                "inEvent=" + inEvent +
                ", eligibleEventsMetaData=" + eligibleEventsMetaData +
                ", eventType='" + eventType + '\'' +
                ", eventUniqueIdentifier='" + eventUniqueIdentifier + '\'' +
                ", isProcessNeedsStop=" + isProcessNeedsStop +
                ", filteredEventsMetaData=" + filteredEventsMetaData +
                '}';
    }

    public Object getInEvent() {
        return inEvent;
    }

    public void setInEvent(T inEvent) {
        this.inEvent = inEvent;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public boolean isProcessNeedsStop() {
        return isProcessNeedsStop;
    }

    public void setProcessNeedsStop(boolean processNeedsStop) {
        isProcessNeedsStop = processNeedsStop;
    }

    public U getEligibleEventsMetaData() {
        return eligibleEventsMetaData;
    }

    public void setEligibleEventsMetaData(U eligibleEventsMetaData) {
        this.eligibleEventsMetaData = eligibleEventsMetaData;
    }

}
