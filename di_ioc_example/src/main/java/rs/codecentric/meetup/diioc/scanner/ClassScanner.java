package rs.codecentric.meetup.diioc.scanner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rs.codecentric.meetup.diioc.utils.ClassUtils;

public class ClassScanner {

    /**
     * Method scans given class and searches for fields annotated with given annotation.
     * 
     * @param clazz
     *            class to scan
     * @param annotation
     *            annotation to search for
     * @return a list of fields or empty list, if no field is annotated with given annotation
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<Field> findAnnotatedFields(Class clazz, Class annotation) {
        List<Field> annotatedFields = new ArrayList<Field>();

        for (Field f : ClassUtils.getAllFields(clazz)) {
            if (f.getAnnotation(annotation) != null) {
                annotatedFields.add(f);
            }
        }

        return annotatedFields;
    }
}
