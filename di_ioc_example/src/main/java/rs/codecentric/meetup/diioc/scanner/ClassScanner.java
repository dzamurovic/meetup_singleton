package rs.codecentric.meetup.diioc.scanner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassScanner {

    private static final ClassScanner INSTANCE = new ClassScanner();

    private ClassScanner() {
    }

    public static ClassScanner getInstace() {
        return INSTANCE;
    }

    /**
     * Method returns a list of all fields of the given class. Field of super classes are included into result.
     * 
     * @param clazz
     *            class to scan
     * @return list of all fields of the given class
     */
    @SuppressWarnings("rawtypes")
    public List<Field> getAllFields(Class clazz) {
        List<Field> fields = new ArrayList<Field>();

        for (Field f : clazz.getDeclaredFields()) {
            fields.add(f);
        }

        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }

        return fields;
    }

    /**
     * Method returns a defining class of a given class. It checks if given class implements an interface and, if it does, returns interface class as defining class, otherwise return given class.
     * 
     * @param cls
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Class getDefiningClass(Class cls) {
        Class[] interfaces = cls.getInterfaces();
        return interfaces.length > 0 ? interfaces[0] : cls;
    }

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

        for (Field f : getAllFields(clazz)) {
            if (f.getAnnotation(annotation) != null) {
                annotatedFields.add(f);
            }
        }

        return annotatedFields;
    }

    /**
     * Method returns the string representation of a given object - description containing list of fields and their values.
     * 
     * @param obj
     * @return
     * @throws Exception
     */
    public String getStructureDescription(Object obj) throws Exception {
        StringBuffer stringBuilder = new StringBuffer("Structure of " + obj.getClass() + " {");

        for (Field f : getAllFields(obj.getClass())) {
            stringBuilder.append("\n\tField: " + f);
            f.setAccessible(true);
            stringBuilder.append("\n\tValue: " + f.get(obj));
        }

        stringBuilder.append("\n}");

        return stringBuilder.toString();
    }
}
