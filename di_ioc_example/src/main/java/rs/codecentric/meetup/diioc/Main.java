package rs.codecentric.meetup.diioc;

import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(DependencyInjectionManager.class);

    public static void main(String args[]) throws Exception {
        final DependencyInjectionManager diManager = DependencyInjectionManager.getInstance();

        Thread t1 = new DependencyInjectionThread("DI-thread-0", diManager);
        Thread t2 = new DependencyInjectionThread("DI-thread-1", diManager);
        Thread t3 = new DependencyInjectionThread("DI-thread-2", diManager);
        Thread t4 = new DependencyInjectionThread("DI-thread-3", diManager);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        logger.info("A couple of empty lines to improve readability...\n\n\n");
        diManager.describeDependencyGraph();
    }

    static class DependencyInjectionThread extends Thread {

        private DependencyInjectionManager diManager;

        public DependencyInjectionThread(String name, DependencyInjectionManager diManager) {
            super(name);
            this.diManager = diManager;
        }

        @Override
        public void run() {
            try {
                diManager.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
