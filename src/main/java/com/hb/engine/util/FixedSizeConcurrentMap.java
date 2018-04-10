package com.hb.engine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class FixedSizeConcurrentMap<K extends Comparable, V> implements Serializable {

    private static int DEFAULT_ENTRIES_SIZE = 20;
    private Logger log = LoggerFactory.getLogger(FixedSizeConcurrentMap.class.getSimpleName());
    private ReadWriteLock readWriteLock = null;
    private LinkedHashMap<K, V> fixedSizeMap = new LinkedHashMap<K, V>(DEFAULT_ENTRIES_SIZE) {
        @Override
        public boolean removeEldestEntry(java.util.Map.Entry<K, V> entry) {
            return this.size() > DEFAULT_ENTRIES_SIZE;
        }
    };

    public FixedSizeConcurrentMap() {
    }

    @PostConstruct
    public void init() {
        readWriteLock = new ReentrantReadWriteLock();
    }

    public void put(K key, V value) {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            getFixedSizeMap().put(key, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * This will be called when there is initial load from DB.
     *
     * @param inMap
     */
    public void putAll(Map<K, V> inMap) {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            getFixedSizeMap().putAll(inMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        Lock readLock = readWriteLock.readLock();
        V value = null;
        try {
            readLock.lock();
            value = getFixedSizeMap().get(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return value;
    }

    public boolean contains(K key) {
        Lock readLock = readWriteLock.readLock();
        boolean isContains = false;
        try {
            readLock.lock();
            isContains = getFixedSizeMap().containsKey(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return isContains;
    }

    public boolean remove(K key) {
        Lock writeLock = readWriteLock.writeLock();
        boolean isRemoved = false;
        try {
            writeLock.lock();
            isRemoved = (getFixedSizeMap().remove(key) != null);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writeLock.unlock();
        }
        return isRemoved;
    }

    public LinkedHashMap<K, V> getFixedSizeMap() {
        return fixedSizeMap;
    }

    public void setFixedSizeMap(LinkedHashMap<K, V> fixedSizeMap) {
        this.fixedSizeMap = fixedSizeMap;
    }


}
