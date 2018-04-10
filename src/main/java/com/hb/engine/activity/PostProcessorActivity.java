package com.hb.engine.activity;

import com.hb.engine.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@Scope("prototype")
public class PostProcessorActivity<T extends Context> implements Activity<Context> {

    private Logger log = LoggerFactory.getLogger(ApplyCriteriaActivity.class.getSimpleName());

    @Override
    public Context process(Context context) {
        log.debug("PostProcessorActivity start ");
        log.debug("PostProcessorActivity.process completed ,after completion context is {}",context);
        context.setProcessNeedsStop(true);
        return context;
    }
}
