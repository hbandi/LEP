package com.hb.query.criteria.types;

import com.hb.model.CriteriaEventActivity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TimesCriteria extends Criteria {

    private int skipCount;
    private int times;

    @Override
    public Criteria apply(Criteria activity) {
        try{
            this.times = this.times + 1;
            return this;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public int getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }

    public int getNoOfTimes() {
        return times;
    }

    public void setNoOfTimes(int noOfTimes) {
        this.times = noOfTimes;
    }

    @Override
    public String toString() {
        return "TimesCriteria{" +
                "skipCount=" + skipCount +
                ", noOfTimes=" + times +
                '}';
    }

}
