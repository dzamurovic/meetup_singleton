package rs.codecentric.meetup.diioc.scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.codecentric.meetup.diioc.annotations.Component;

public class PackageScannerTest {

    private PackageScanner packageScanner;

    @Before
    public void setUp() {
        packageScanner = PackageScanner.getInstance();
    }

    @Test
    public void testFindAnnotatedClasses() throws Exception {
        Assert.assertEquals(0, packageScanner.findAnnotatedClasses("asd", Component.class).size());
        Assert.assertEquals(2, packageScanner.findAnnotatedClasses("testdata", Component.class).size());
    }

    @Test
    public void testGetClasses() throws Exception {
        Assert.assertEquals(0, packageScanner.getClasses("asd").size());
        Assert.assertEquals(4, packageScanner.getClasses("testdata").size());
    }

}
