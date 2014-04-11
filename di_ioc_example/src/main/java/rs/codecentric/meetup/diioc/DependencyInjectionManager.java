package rs.codecentric.meetup.diioc;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.codecentric.meetup.diioc.annotations.Component;
import rs.codecentric.meetup.diioc.annotations.Inject;
import rs.codecentric.meetup.diioc.scanner.ClassScanner;
import rs.codecentric.meetup.diioc.scanner.PackageScanner;

public class DependencyInjectionManager {

    private static Logger logger = Logger.getLogger(DependencyInjectionManager.class);

    private static final DependencyInjectionManager INSTANCE = new DependencyInjectionManager();

    private PackageScanner packageScanner;
    private ClassScanner classScanner;

    @SuppressWarnings("rawtypes")
    private Map<Class, Object> graph;

    private boolean initialized;

    @SuppressWarnings("rawtypes")
    private DependencyInjectionManager() {
        graph = new Hashtable<Class, Object>();

        packageScanner = PackageScanner.getInstance();
        classScanner = ClassScanner.getInstace();

        initialized = false;
    }

    public static DependencyInjectionManager getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("rawtypes")
    public synchronized void run() throws Exception {
        logger.info("Dependency injection run started...");
        if (initialized) {
            logger.info("Dependency injection already performed. This run will be skipped.");
            return;
        }

        // Find classes annotated with @Component.
        List<Class> components = packageScanner.findAnnotatedClasses("rs.codecentric.meetup.diioc.example", Component.class);

        // For each component...
        for (Class component : components) {
            Class definingClass = classScanner.getDefiningClass(component);
            // ... check if its instance already exists...
            if (!graph.containsKey(definingClass)) {
                // ... and instantiate it, if it doesn't.
                String className = component.getName();
                logger.info("Instantiating class " + className);
                graph.put(definingClass, Class.forName(component.getName()).newInstance());
            }
        }

        // For each object that is created...
        for (Iterator<Class> classIterator = graph.keySet().iterator(); classIterator.hasNext();) {
            Class definingClass = classIterator.next();
            Object object = graph.get(definingClass);

            // ... find dependencies it needs, ...
            for (Field f : classScanner.findAnnotatedFields(object.getClass(), Inject.class)) {
                if (!graph.containsKey(f.getType())) {
                    // ... throw an error if a dependency cannot be satisfied, ...
                    throw new RuntimeException("Dependency cannot be satisfied! No instance of " + f.getType() + " found to satisfy dependency " + f);
                }
                boolean accessModified = false;
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                    accessModified = true;
                }

                // ... or set a value of the dependency.
                Object dependency = graph.get(f.getType());
                logger.info("Injecting " + dependency + " into " + f);
                f.set(object, dependency);

                if (accessModified) {
                    // I am a good citizen.
                    f.setAccessible(false);
                }
            }
        }

        initialized = true;
        logger.info("Dependency injection run finished.");
    }

    @SuppressWarnings("rawtypes")
    public void describeDependencyGraph() throws Exception {
        for (Iterator<Class> classIterator = graph.keySet().iterator(); classIterator.hasNext();) {
            Class definingClass = classIterator.next();
            logger.info(ClassScanner.getInstace().getStructureDescription(graph.get(definingClass)));
        }
    }

}
