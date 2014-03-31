package rs.codecentric.meetup.singleton;

import org.apache.log4j.Logger;

public class Singleton02 {

    private static Singleton02 instance = null;

    private static boolean isFirstThread = true;
    private static Logger logger = Logger.getRootLogger();

    private Singleton02() {
        // Do nothing.
    }

    // Not synchronized, not thread safe.
    public static Singleton02 getInstance() {
        if (instance == null) {
            simulateMultiThreadEnvironment();
            instance = new Singleton02();
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
