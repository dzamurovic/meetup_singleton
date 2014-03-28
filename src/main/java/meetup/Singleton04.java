package meetup;

import org.apache.log4j.Logger;

public class Singleton04 {

    private static Singleton04 instance = null;

    private static boolean isFirstThread = true;
    private static Logger logger = Logger.getRootLogger();

    private Singleton04() {
        // Do nothing.
    }

    // Synchronized, performance optimization, not thread safe.
    public static Singleton04 getInstance() {
        if (instance == null) {
            synchronized (Singleton04.class) {
                simulateMultiThreadEnvironment();
                instance = new Singleton04();
                logger.info("Creating singleton...");
            }
        }
        return instance;
    }

    private static void simulateMultiThreadEnvironment() {
        try {
            if (isFirstThread) {
                isFirstThread = false;

                // Give some time to the second thread to do its work.
                logger.info("Sleeping...");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            logger.warn("Sleep interrupted!");
        }
    }
}
