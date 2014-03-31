package meetup;

import static org.junit.Assert.*;

import org.junit.Test;

import rs.codecentric.meetup.singleton.Singleton06;

public class Singleton06Test {

    private static Singleton06 instance = null;

    private volatile AssertionError error = null;

    private class SingletonTestRunnable implements Runnable {

        @Override
        public void run() {
            // Get a reference...
            Singleton06 s = Singleton06.getInstance();

            // ... and protect it from multi-threaded access.
            synchronized (Singleton06Test.class) {
                if (instance == null) {
                    instance = s;
                }
            }

            // Local instance must be equal to the global one. Otherwise there are two different instances.
            try {
                assertTrue(instance == s);
            } catch (AssertionError exc) {
                error = exc;
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(new SingletonTestRunnable(), "Thread-1");
        Thread t2 = new Thread(new SingletonTestRunnable(), "Thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        if (error != null) {
            throw error;
        }
    }

}
