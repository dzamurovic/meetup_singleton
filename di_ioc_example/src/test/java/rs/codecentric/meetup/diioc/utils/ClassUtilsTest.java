package rs.codecentric.meetup.diioc.utils;

import org.junit.Assert;
import org.junit.Test;

import testdata.ChildClass;
import testdata.ParentClass;

public class ClassUtilsTest {

    @Test
    public void testGetAllFields() {
        Assert.assertEquals(0, ClassUtils.getAllFields(ClassUtilsTest.class).size());

        Assert.assertEquals(3, ClassUtils.getAllFields(ParentClass.class).size());

        Assert.assertEquals(2, ClassUtils.getAllFields(ChildClass.class).size());
    }
}
