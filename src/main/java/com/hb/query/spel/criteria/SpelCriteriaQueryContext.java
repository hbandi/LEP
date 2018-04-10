package com.hb.query.spel.criteria;

import com.hb.query.spel.filter.SpelFilterQueryContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "prototype")
public class SpelCriteriaQueryContext<T extends StringBuffer> extends SpelFilterQueryContext<StringBuffer> {

    public SpelCriteriaQueryContext noOfTimesToMetCriteria(int value) {
        eqForNumber("noOfTimes", value);
        return this;
    }


}
