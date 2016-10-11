package test.com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import test.com.Api.Cache;
import test.com.Impl.MemCache;

import java.io.IOException;

public class MemCacheTest extends TestCase {

    protected Cache<String, String> cache;
    protected String key = "Key";
    protected String key2 = "Key2";
    protected String value = "Value";

    public MemCacheTest(String testName) {
        super(testName);
        cache = new MemCache<String, String>();
    }

    public static Test suite() {
        return new TestSuite(MemCacheTest.class);
    }

    public void testPutGet() throws IOException, ClassNotFoundException {
        cache.put(key, value);
        assertEquals(value, cache.get(key));
    }

    public void testContains() throws IOException {
        cache.put(key, value);
        assertTrue(cache.contains(key));
        assertFalse(cache.contains(key2));
    }

    public void testRemove() throws IOException {
        cache.put(key, value);
        cache.remove(key);
        assertFalse(cache.contains(key));
    }
}
