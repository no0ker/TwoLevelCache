package test.com;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.com.Impl.HddCache;

public class HddCacheTest extends MemCacheTest {

    private String path = "c:\\test";

    public HddCacheTest(String testName) {
        super(testName);
        super.cache = new HddCache<String, String>(path);
    }

    public static Test suite() {
        return new TestSuite(HddCacheTest.class);
    }

}
