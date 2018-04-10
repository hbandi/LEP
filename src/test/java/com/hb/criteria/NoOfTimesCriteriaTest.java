package com.hb.criteria;


import com.hb.db.event.MongoConfig;
import com.hb.query.criteria.types.TimesCriteria;
import com.hb.query.spel.criteria.SpelCriteriaQueryContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoConfig.class)
public class NoOfTimesCriteriaTest {

    @Autowired
    private SpelCriteriaQueryContext spelQueryContext;

    @Test
    public void whenCreateNoOfTimesEqualQuery_thenValidateSpel() {
        String spel = spelQueryContext.startComplexExpression().
                noOfTimesToMetCriteria(2).endComplexExpression().getQuery();
        TimesCriteria criteriaType = new TimesCriteria();
        criteriaType.setCriteriaSpel(spel);
        criteriaType.setNoOfTimes(2);
        System.out.println("criteriaType :: " + criteriaType + "   Spel is " + spel);
        boolean bool = criteriaType.isMet(criteriaType);

        Assert.assertEquals(bool, true);
    }

    @Test
    public void whenCreateNoOfTimesNotEqualQuery_thenValidateSpel() {
        String spel = spelQueryContext.startComplexExpression().
                noOfTimesToMetCriteria(3).endComplexExpression().getQuery();
        TimesCriteria criteriaType = new TimesCriteria();
        criteriaType.setCriteriaSpel(spel);
        criteriaType.setNoOfTimes(2);
        System.out.println("criteriaType :: " + criteriaType + "   Spel is " + spel);
        boolean bool = criteriaType.isMet(criteriaType);
        Assert.assertEquals(bool, false);
    }
}
