package com.hb.query.spel.filter;

import com.hb.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class SpelFilterQuery<T> implements Query<T> {

    private Logger log = LoggerFactory.getLogger(SpelFilterQuery.class.getSimpleName());

    @Override
    public boolean eval(String inSpel, T inEvent) {
        log.debug("SpelFilterQuery:eval has been Called,SPEL {},inEvent {} ", inSpel, inEvent);
        try {
            if ((inSpel == null || inSpel.isEmpty()) || inEvent == null) {
                log.debug("SpelFilterQuery:eval either spel or inEvent Null,So exit with false ");
                return false;
            }
            StandardEvaluationContext spelContext = new StandardEvaluationContext();
            /**
             * TODO need to find actual Object for spel Evaluation.
             */
            spelContext.setRootObject(inEvent);
            return new SpelExpressionParser().parseExpression(inSpel).getValue(spelContext, Boolean.class) ? true : false;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Found EXPN while evaluating SPEL {} ", ex.getMessage());
        }
        return false;
    }
}
