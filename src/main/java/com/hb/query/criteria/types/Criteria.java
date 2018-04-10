package com.hb.query.criteria.types;

import com.hb.model.CriteriaEventActivity;
import com.hb.query.criteria.CriteriaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.Serializable;

public abstract class Criteria implements Serializable {

    private Logger log = LoggerFactory.getLogger(Criteria.class.getSimpleName());

    private String criteriaSpel;

    private CriteriaType criteriaType;

    public abstract Criteria apply(Criteria activity);

    public CriteriaType getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(CriteriaType criteriaType) {
        this.criteriaType = criteriaType;
    }

    public String getCriteriaSpel() {
        return criteriaSpel;
    }

    public void setCriteriaSpel(String criteriaSpel) {
        this.criteriaSpel = criteriaSpel;
    }

    public Boolean isMet(Criteria expectedObject) {
        log.debug("Criteria:isMet has been Called,expectedObject {} ", expectedObject);
        try {
            if (expectedObject == null || (expectedObject.criteriaSpel == null || expectedObject.criteriaSpel.isEmpty())) {
                log.debug("Criteria:isMet either spel or expectedObject is  Null,So exit with false ");
                return false;
            }
            StandardEvaluationContext spelContext = new StandardEvaluationContext();
            spelContext.setRootObject(expectedObject);
            return new SpelExpressionParser().parseExpression(expectedObject.criteriaSpel).getValue(spelContext, Boolean.class) ? true : false;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Found EXPN while evaluating SPEL {} ", ex.getMessage());
        }

        return false;
    }


}
