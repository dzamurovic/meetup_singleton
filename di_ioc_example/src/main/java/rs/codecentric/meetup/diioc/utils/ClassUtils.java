package rs.codecentric.meetup.diioc.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {

    public static List<Field> getAllFields(Class clazz) {
        List<Field> fields = new ArrayList<Field>();

        for (Field f : clazz.getDeclaredFields()) {
            fields.add(f);
        }

        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }

        return fields;
    }

}
