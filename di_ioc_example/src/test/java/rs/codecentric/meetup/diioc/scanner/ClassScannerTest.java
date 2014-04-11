package rs.codecentric.meetup.diioc.scanner;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;
import testdata.ChildClass;
import testdata.ParentClass;

public class ClassScannerTest {

    private ClassScanner classScanner;

    @Before
    public void setUp() {
        classScanner = ClassScanner.getInstace();
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

}
