package rs.codecentric.meetup.singleton;

import org.apache.log4j.Logger;

/**
 * Synchronized, thread safe.
 */
public class Singleton03 {

    private static Singleton03 instance = null;

    private static boolean isFirstThread = true;
    private static Logger logger = Logger.getRootLogger();

    private Singleton03() {
        // Do nothing.
    }

    public synchronized static Singleton03 getInstance() {
        if (instance == null) {
            simulateMultiThreadEnvironment();
            instance = new Singleton03();
        }
        logger.info("Creating singleton...");
        return instance;
    }

    private static void simulateMultiThreadEnvironment() {
        try {
            if (isFirstThread) {
                isFirstThread = false;

                // Give some time to the second thread to do its work.
                logger.info("Sleeping...");
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            logger.warn("Sleep interrupted!");
        }
    }
}
