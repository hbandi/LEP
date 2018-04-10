package com.hb.query;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

@Configuration
@ComponentScan("com.hb.query")
public abstract class QueryContext<T> implements Serializable {

    protected T query;
    /*
    queryType is mostly SPEL or OGNL
     */
    protected QueryType queryType;

    public abstract String getQuery();

    public abstract QueryContext startComplexExpression();

    public abstract QueryContext endComplexExpression();

    public abstract QueryContext and();

    public abstract QueryContext or();

    public abstract QueryContext gt(String key, String value);

    public abstract QueryContext le(String key, String value);

    public abstract QueryContext ge(String key, String value);

    public abstract QueryContext lt(String key, String value);

    public abstract QueryContext eq(String key, String value);

    public abstract QueryContext eqForNumber(String key, int value);

    public abstract QueryContext eqOp(String key, String value);

    public abstract QueryContext matches(String key, String separator, List<String> values);

    public abstract QueryContext containsAll(String key, List<String> values);

    public abstract QueryContext notContainsAll(String key, List<String> values);

    public abstract QueryContext contains(String key, String value);

    public abstract QueryContext notContains(String key, String value);

    public String getQueryType() {
        return queryType.toString();
    }

    /*
        Operations specific to Criteria SPEL
   */
    public abstract QueryContext noOfTimesToMetCriteria(int value);


}
