package rs.codecentric.meetup.diioc.scanner;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;
import testdata.AnInterface;
import testdata.ChildClass;
import testdata.ClassToExtend;
import testdata.ParentClass;

public class ClassScannerTest {

    private ClassScanner classScanner;

    @Before
    public void setUp() {
        classScanner = ClassScanner.getInstace();
    }

    @Test
    public void testGetAllFields() {
        Assert.assertEquals(0, classScanner.getAllFields(AnInterface.class).size());

        Assert.assertEquals(1, classScanner.getAllFields(ClassScanner.class).size());

        Assert.assertEquals(2, classScanner.getAllFields(ChildClass.class).size());

        Assert.assertEquals(3, classScanner.getAllFields(ParentClass.class).size());
    }

    @Test
    public void testFindAnnotatedFieldsNull() {
        List<Field> fields = classScanner.findAnnotatedFields(ChildClass.class, Component.class);
        assertEquals(0, fields.size());
    }

    @Test
    public void testFindAnnotatedFields() {
        List<Field> fields = classScanner.findAnnotatedFields(ParentClass.class, Inject.class);
        assertEquals(3, fields.size());
    }

    @Test
    public void testGetDefiningClass() {
        Assert.assertEquals(ParentClass.class, classScanner.getDefiningClass((ParentClass.class)));

        Assert.assertEquals(AnInterface.class, classScanner.getDefiningClass((ClassToExtend.class)));
    }
}
