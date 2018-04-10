package com.hb.spel;


import com.hb.db.event.MongoConfig;
import com.hb.query.spel.criteria.SpelCriteriaQueryContext;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class SpelCriteriaUnitTest {

    @Autowired
    private SpelCriteriaQueryContext spelCriteriaQueryContext;

    @Test
    public void whenCreateCriteriaUsingEq_thenValidateSpel() {
        String spel = spelCriteriaQueryContext.startComplexExpression().
                noOfTimesToMetCriteria(2).endComplexExpression().getQuery();
        Assert.assertEquals(spel, StringEscapeUtils.escapeJava("((noOfTimes eq 2))"));
    }


}
