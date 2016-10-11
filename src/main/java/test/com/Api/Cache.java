package test.com.Api;

import java.io.IOException;

public interface Cache<K, V> {
    void put(K key, V value) throws IOException;
    V get(K key) throws IOException, ClassNotFoundException;
    boolean contains(K key);
    void remove(K key);
}
