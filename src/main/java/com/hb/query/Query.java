package com.hb.query;

@FunctionalInterface
public interface Query<T> {
    public boolean eval(String spel, T inEvent);
}
