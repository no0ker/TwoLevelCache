package test.com.Api;

import java.io.IOException;
import java.io.Serializable;

public interface Cache<K, V extends Serializable> {
    void put(K key, V value) throws IOException;
    V get(K key) throws IOException, ClassNotFoundException;
    boolean contains(K key);
    void remove(K key);
    void clear();
}
