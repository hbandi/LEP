package com.hb.spel;


import com.hb.db.event.MongoConfig;
import com.hb.query.spel.filter.SpelFilterQueryContext;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
@Component
public class SpelFilterUnitTest {

    @Autowired
    private SpelFilterQueryContext spelFilterQueryContext;

    @Test
    public void whenCreateUsingEqAndGT_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").and().gt("age", "18").endComplexExpression().getQuery();
        Assert.assertEquals(spel, StringEscapeUtils.escapeJava("((customerName eq 'user389') and (age gt '18'))"));
    }

    @Test
    public void whenCreateUsingEqAndLT_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").and().lt("age", "18").endComplexExpression().getQuery();
        Assert.assertEquals(spel, StringEscapeUtils.escapeJava("((customerName eq 'user389') and (age lt '18'))"));
    }

    @Test
    public void whenCreateUsingEqAndEq_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").and().eq("age", "18").endComplexExpression().getQuery();
        Assert.assertEquals(spel, StringEscapeUtils.escapeJava("((customerName eq 'user389') and (age eq '18'))"));
    }

    @Test
    public void whenCreateUsingEqOEq_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().eq("age", "18").endComplexExpression().getQuery();
        Assert.assertEquals(spel, StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age eq '18'))"));

        try {
            class Customer {
                public String customerName;
                public String age;
            }
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(spel);
            System.out.println("Expression is : " + expression);
            Customer obj = (Customer) expression.getValue(Customer.class);
            System.out.println("object is :: " + obj);
        } catch (Exception ex) {
            System.out.println("exception found " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void whenCreateUsingEqOrltAnd_LtOrGt_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().lt("age", "18").and().startComplexExpression().lt("height", "6").or().gt("salary", "10000").
                endComplexExpression().endComplexExpression().getQuery();
        Assert.assertEquals(spel, StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age lt '18') and ((height lt '6') or (salary gt '10000')))"));
    }

    @Test
    public void whenCreateUsingEqOrltAnd_LtOrGt_Matches_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().lt("age", "18").and().startComplexExpression().lt("height", "6").or().gt("salary", "10000").
                endComplexExpression().and().matches("department", "|", Arrays.asList("12", "13", "14", "15", "16")).endComplexExpression().getQuery();
        Assert.assertEquals(spel,
                StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age lt '18') and ((height lt '6') or (salary gt '10000')) and" +
                        " (department matches '12|13|14|15|16'))"));
    }

    @Test
    public void whenCreateUsingEqOrltAnd_LtOrGt_Matches_ContainsAll_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().lt("age", "18").and().startComplexExpression().lt("height", "6").or().gt("salary", "10000").
                endComplexExpression().and().matches("department", "|", Arrays.asList("12", "13", "14", "15", "16")).and().
                containsAll("regionIds", Arrays.asList("121", "138", "149", "150", "164")).endComplexExpression().getQuery();
        Assert.assertEquals(spel,
                StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age lt '18') and ((height lt '6') or (salary gt '10000')) and" +
                        " (department matches '12|13|14|15|16') and ({121,138,149,150,164}.containsAll(regionIds)))"));
    }

    @Test
    public void whenCreateUsingEqOrltAnd_LtOrGt_Matches_NotContainsAll_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().lt("age", "18").and().startComplexExpression().lt("height", "6").or().gt("salary", "10000").
                endComplexExpression().and().matches("department", "|", Arrays.asList("12", "13", "14", "15", "16")).and().
                notContainsAll("regionIds", Arrays.asList("121", "138", "149", "150", "164")).endComplexExpression().getQuery();
        Assert.assertEquals(spel,
                StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age lt '18') and ((height lt '6') or (salary gt '10000')) and" +
                        " (department matches '12|13|14|15|16') and (!{121,138,149,150,164}.containsAll(regionIds))"));
    }

    @Test
    public void whenCreateUsingEqOrltAnd_LtOrGt_Matches_Contains_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().lt("age", "18").and().startComplexExpression().lt("height", "6").or().gt("salary", "10000").
                endComplexExpression().and().matches("department", "|", Arrays.asList("12", "13", "14", "15", "16")).and().
                contains("regionIds", "56").endComplexExpression().getQuery();
        Assert.assertEquals(spel,
                StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age lt '18') and ((height lt '6') or (salary gt '10000')) and" +
                        " (department matches '12|13|14|15|16') and ({56}.contains(regionIds))"));
    }

    @Test
    public void whenCreateUsingEqOrltAnd_LtOrGt_Matches_NotContains_thenValidateSpel() {
        String spel = spelFilterQueryContext.startComplexExpression().
                eq("customerName", "user389").or().lt("age", "18").and().startComplexExpression().lt("height", "6").or().gt("salary", "10000").
                endComplexExpression().and().matches("department", "|", Arrays.asList("12", "13", "14", "15", "16")).and().
                notContains("regionIds", "56").endComplexExpression().getQuery();


        Assert.assertEquals(spel,
                StringEscapeUtils.escapeJava("((customerName eq 'user389') or (age lt '18') and ((height lt '6') or (salary gt '10000')) and" +
                        " (department matches '12|13|14|15|16') and (!{56}.contains(regionIds))"));

    }


}
