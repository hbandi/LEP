package com.hb.engine.activity;

import com.hb.engine.context.Context;
import com.hb.model.CriteriaEventActivity;
import com.hb.query.criteria.types.Criteria;
import com.hb.query.criteria.types.CriteriaTypeMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(4)
@Scope("prototype")
public class ApplyCriteriaActivity<T extends Context> implements Activity<Context> {

    private Logger log = LoggerFactory.getLogger(ApplyCriteriaActivity.class.getSimpleName());

    @Override
    public Context process(Context context) {

        log.debug("ApplyCriteriaActivity start context {}",context);
        try {
            List<Criteria> valaCriterias=context.getEligibleEventsMetaData().getValaProcessCriterias();
            if(valaCriterias!=null && valaCriterias.size()>0){
                for(Criteria criteria:valaCriterias){
                    Criteria mappedCriteria=CriteriaTypeMapping.getCriteriaTypeMap().get(valaCriterias.get(0).getCriteriaType());
                    CriteriaEventActivity eventActivity=new CriteriaEventActivity();
                    eventActivity.setActivityId("123");
                    eventActivity.setCriteriaId("190");
                    Criteria createdCriteria =mappedCriteria.apply(criteria);
                    if(createdCriteria!=null){
                        boolean isMet =mappedCriteria.isMet(valaCriterias.get(0));
                        log.debug("criteria MET ");
                    }
                    eventActivity.setCriteria(createdCriteria);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return context;
    }
}
