package rs.codecentric.meetup.diioc;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;
import rs.codecentric.meetup.diioc.scanner.ClassScanner;
import rs.codecentric.meetup.diioc.scanner.PackageScanner;

public class Main {

    @SuppressWarnings("rawtypes")
    public static void main(String args[]) throws Exception {
        Map<Class, Object> graph = new Hashtable<Class, Object>();

        PackageScanner packageScanner = PackageScanner.getInstance();
        List<Class> components = packageScanner.findAnnotatedClasses("rs.codecentric.meetup.diioc.example", Component.class);

        // 1. find classes annotated with @Component
        // 2. for each "component"
        // 2a. if it is not instantiated, instantiate it
        // 2b. put it in the graph
        // 3. build component dependency structure
        // 3a. for each @Inject, check if graph contains instance of annotated type
        // 3b. set its value to the component property

        for (Class component : components) {
            Class definingClass = getDefiningClass(component);
            if (!graph.containsKey(definingClass)) {
                graph.put(definingClass, Class.forName(component.getName()).newInstance());
            }
        }

        ClassScanner classScanner = ClassScanner.getInstace();
        for (Iterator<Class> classIterator = graph.keySet().iterator(); classIterator.hasNext();) {
            Class definingClass = classIterator.next();
            Object object = graph.get(definingClass);
            for (Field f : classScanner.findAnnotatedFields(object.getClass(), Inject.class)) {
                if (!graph.containsKey(f.getType())) {
                    throw new RuntimeException("Dependency cannot be satisfied! No instance of " + f.getType() + " found to satisfy dependency " + f);
                }
                boolean accessModified = false;
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                    accessModified = true;
                }
                f.set(object, graph.get(f.getType()));
                if (accessModified) {
                    f.setAccessible(false);
                }
            }
        }

        for (Iterator<Class> classIterator = graph.keySet().iterator(); classIterator.hasNext();) {
            Class definingClass = classIterator.next();
            Object object = graph.get(definingClass);
            System.out.println(classScanner.getStructureDescription(object));
        }

    }

    /**
     * Check if given class implements an interface. If it does, return interface class as defining class, otherwise return this class.
     * 
     * @param cls
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Class getDefiningClass(Class cls) {
        Class[] interfaces = cls.getInterfaces();
        return interfaces.length > 0 ? interfaces[0] : cls;
    }

}
