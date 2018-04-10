package com.hb.query.criteria;

public enum CriteriaType {

    TimesCriteria("X times for an event");

    private String desc;

    CriteriaType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
