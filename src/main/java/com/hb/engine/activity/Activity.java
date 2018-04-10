package com.hb.engine.activity;

import com.hb.engine.context.Context;

public interface Activity<T extends Context> {

    public abstract T process(T context);

    default public String getActivityName() {
        return this.getClass().getSimpleName();
    }

}
