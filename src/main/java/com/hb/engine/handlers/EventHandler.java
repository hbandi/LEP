package com.hb.engine.handlers;

import com.hb.engine.context.Context;

public interface EventHandler<T extends Context> {
    public T handleEvent(T context);
}
