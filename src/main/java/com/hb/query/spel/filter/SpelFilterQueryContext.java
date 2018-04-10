package com.hb.query.spel.filter;


import com.hb.query.QueryContext;
import com.hb.query.spel.SpelConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = "prototype")
public class SpelFilterQueryContext<T extends StringBuffer> extends QueryContext<StringBuffer> {

    public SpelFilterQueryContext() {
        query = new StringBuffer();
    }

    @Override
    public String getQuery() {
        return query.toString();
    }

    public SpelFilterQueryContext startComplexExpression() {
        query.append(SpelConstants.startSimpleBrace);
        return this;
    }

    public SpelFilterQueryContext endComplexExpression() {
        query.append(SpelConstants.endSimpleBrace);
        return this;
    }

    public SpelFilterQueryContext and() {
        query.append(SpelConstants.AND);
        return this;
    }

    public QueryContext or() {
        query.append(SpelConstants.OR);
        return this;
    }

    public SpelFilterQueryContext gt(String key, String value) {
        expressionFromValue(key, SpelConstants.gt, value);
        return this;
    }

    public SpelFilterQueryContext le(String key, String value) {
        expressionFromValue(key, SpelConstants.le, value);
        return this;
    }

    public SpelFilterQueryContext ge(String key, String value) {
        expressionFromValue(key, SpelConstants.ge, value);
        return this;
    }

    public SpelFilterQueryContext lt(String key, String value) {
        expressionFromValue(key, SpelConstants.lt, value);
        return this;
    }

    public SpelFilterQueryContext eq(String key, String value) {
        expressionFromStringValue(key, SpelConstants.eq, value);
        return this;
    }

    public SpelFilterQueryContext eqForNumber(String key, int value) {
        expressionFromNumberValue(key, SpelConstants.eq, value);
        return this;
    }

    public SpelFilterQueryContext eqOp(String key, String value) {
        expressionFromValue(key, SpelConstants.eqOp, value);
        return this;
    }

    public SpelFilterQueryContext matches(String key, String separator, List<String> values) {
        expressionForMatches(key, separator, values);
        return this;
    }

    public SpelFilterQueryContext containsAll(String key, List<String> values) {
        expressionForContainsAll(key, values);
        return this;
    }

    public SpelFilterQueryContext notContainsAll(String key, List<String> values) {
        expressionForNotContainsAll(key, values);
        return this;
    }

    public SpelFilterQueryContext contains(String key, String value) {
        expressionForContains(key, value);
        return this;
    }

    public SpelFilterQueryContext notContains(String key, String value) {
        expressionForNotContain(key, value);
        return this;
    }

    private SpelFilterQueryContext expressionFromStringValue(String key, String op, String value) {
        query.append(SpelConstants.startSimpleBrace).append(key).append(op).append("'").append(value).append("'").append(SpelConstants.endSimpleBrace);
        return this;
    }

    private SpelFilterQueryContext expressionFromNumberValue(String key, String op, int value) {
        query.append(SpelConstants.startSimpleBrace).append(key).append(op).append(value).append(SpelConstants.endSimpleBrace);
        return this;
    }

    private SpelFilterQueryContext expressionFromValue(String key, String op, String value) {
        query.append(SpelConstants.startSimpleBrace).append(key).append(op).append("'").append(value).append("'").append(SpelConstants.endSimpleBrace);
        return this;
    }

    private SpelFilterQueryContext expressionForMatches(String key, String separator, List<String> values) {

        String condition = SpelConstants.OR.equalsIgnoreCase(separator) ? "|" :
                SpelConstants.AND.equalsIgnoreCase(separator) ? "&" : null;
        query.append(SpelConstants.startSimpleBrace).append(key).append(SpelConstants.matches).append(SpelConstants.apostrophe).append(String.join("|", values)).append(SpelConstants.apostrophe).append(SpelConstants.endSimpleBrace);
        return this;
    }

    private SpelFilterQueryContext expressionForContainsAll(String key, List<String> values) {
        query.append(SpelConstants.startSimpleBrace).append(SpelConstants.startBrace).append(String.join(",", values)).
                append(SpelConstants.endBrace).append(SpelConstants.containsAll).append(key).append(SpelConstants.endSimpleBrace).append(SpelConstants.endSimpleBrace);
        return this;
    }

    public SpelFilterQueryContext expressionForContains(String key, String value) {
        query.append(SpelConstants.startSimpleBrace).append(SpelConstants.startBrace).append(String.join(",", value)).
                append(SpelConstants.endBrace).append(SpelConstants.contains).append(key).append(SpelConstants.endSimpleBrace);
        return this;

    }

    private SpelFilterQueryContext expressionForNotContainsAll(String key, List<String> values) {
        /*assert Objects.isNull(key);
        assert Objects.isNull(values);*/
        query.append(SpelConstants.startSimpleBrace).append("!").append(SpelConstants.startBrace).append(String.join(",", values)).
                append(SpelConstants.endBrace).append(SpelConstants.containsAll).append(key).append(SpelConstants.endSimpleBrace);
        return this;
    }

    private SpelFilterQueryContext expressionForNotContain(String key, String value) {
        /*assert Objects.isNull(key);
        assert Objects.isNull(value);*/
        query.append(SpelConstants.startSimpleBrace).append("!").append(SpelConstants.startBrace).append(String.join(",", value)).
                append(SpelConstants.endBrace).append(SpelConstants.contains).append(key).append(SpelConstants.endSimpleBrace);
        return this;
    }

    public QueryContext noOfTimesToMetCriteria(int value) {
        try {
            throw new UnsupportedOperationException("Invalid operation for the filter");
        } catch (java.lang.UnsupportedOperationException e) {
            e.printStackTrace();
        }
        return this;
    }
}
