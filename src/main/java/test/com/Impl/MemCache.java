package test.com.Impl;

import test.com.Api.Cache;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemCache<K, V extends Serializable> implements Cache<K, V> {

    private final Map<K, V> innerMap;

    public MemCache() {
        innerMap = new ConcurrentHashMap<K, V>();
    }

    public void put(K key, V value) {
        innerMap.put(key, value);
    }

    public V get(K key) {
        return innerMap.get(key);
    }

    public boolean contains(K key) {
        return innerMap.containsKey(key);
    }

    public void remove(K key) {
        innerMap.remove(key);
    }

    public void clear() {
        innerMap.clear();
    }
}
