package test.com.Impl;

import test.com.Api.Cache;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheDecorator<K, V extends Serializable> implements Cache<K, V> {

    public static final int DEFAULT_SIZE = 10;
    public static enum Strategy {MEMORY, HDD};

    private Strategy currentCtrategy;
    private int maxSize;
    private AtomicInteger currentSize = new AtomicInteger(0);
    private String path;
    private Cache<K, V> innerCache;

    public CacheDecorator(Strategy currentCtrategy, String path, int maxSize) {
        this.currentCtrategy = currentCtrategy;
        this.maxSize = maxSize;
        this.path = path;

        this.currentCtrategy = currentCtrategy;
        if (Strategy.MEMORY.equals(currentCtrategy)) {
            innerCache = new MemCache<K, V>();
        } else if (Strategy.HDD.equals(currentCtrategy)) {
            if (path == null) {
                throw new IllegalArgumentException("Strategy.HDD requres path.");
            }
            innerCache = new HddCache<K, V>(path);
        }
    }

    public CacheDecorator(Strategy currentCtrategy, String path) {
        this(currentCtrategy, path, DEFAULT_SIZE);
    }

    public CacheDecorator(Strategy currentCtrategy, int maxSize) {
        this(currentCtrategy, null, maxSize);
    }

    public CacheDecorator(Strategy currentCtrategy) {
        this(currentCtrategy, null, DEFAULT_SIZE);
    }

    public void put(K key, V value) throws IOException {
        if((currentSize.get() + 1) > maxSize){
            throw new IllegalArgumentException("current size is too large");
        }
        innerCache.put(key, value);
        currentSize.addAndGet(1);
    }

    public V get(K key) throws IOException, ClassNotFoundException {
        return innerCache.get(key);
    }

    public boolean contains(K key) {
        return innerCache.contains(key);
    }

    public void remove(K key) {
        innerCache.remove(key);
        currentSize.addAndGet(-1);
    }

    public void clear() {
        innerCache.clear();
        currentSize.set(0);
    }
}
