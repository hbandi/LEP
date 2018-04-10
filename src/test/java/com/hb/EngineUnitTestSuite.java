package com.hb;

import com.hb.criteria.NoOfTimesCriteriaTest;
import com.hb.db.EventMetaDataCacheTest;
import com.hb.db.event.MongoConfig;
import com.hb.spel.SpelCriteriaUnitTest;
import com.hb.spel.SpelFilterUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Suite.class)
@ContextConfiguration(classes = MongoConfig.class)
@Suite.SuiteClasses({SpelFilterUnitTest.class, SpelCriteriaUnitTest.class, NoOfTimesCriteriaTest.class,EventMetaDataCacheTest.class})
public class EngineUnitTestSuite {
}
