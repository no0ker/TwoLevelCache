package test.com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import test.com.Api.Cache;
import test.com.Impl.CacheDecorator;

import java.io.IOException;

public class CacheDecoratorTest extends TestCase {
    public CacheDecoratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MemCacheTest.class);
    }

    public void testConstructor() {
        try {
            new CacheDecorator(CacheDecorator.Strategy.HDD);
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    public void testMaxSize() throws IOException {
        try {
            Cache<String, String> cache = new CacheDecorator<String, String>(CacheDecorator.Strategy.HDD, 2);
            cache.put("1", "1a");
            cache.put("2", "2a");
            cache.put("3", "3a");
        } catch (Exception e){
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }
}
