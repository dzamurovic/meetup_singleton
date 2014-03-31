package meetup;

import org.apache.log4j.Logger;

public class VolatileExample {

    public long number = 0L;

    static final long ZERO = 0L;
    static final long NINES = 999999999999999999L;

    static class VolatileExampleThread extends Thread {
        private VolatileExample caller;
        private boolean setToZero;

        Logger logger = Logger.getRootLogger();

        VolatileExampleThread(VolatileExample caller, boolean setToZero) {
            super();
            this.caller = caller;
            this.setToZero = setToZero;
        }

        @Override
        public void run() {
            super.run();
            while (true) {
                long value = caller.number;
                if (value != VolatileExample.ZERO && value != VolatileExample.NINES) {
                    logger.info(value);
                }
                caller.number = setToZero ? VolatileExample.ZERO : VolatileExample.NINES;
            }
        }
    }

    public static void main(String args[]) {
        VolatileExample caller = new VolatileExample();

        VolatileExampleThread t1 = new VolatileExampleThread(caller, true);
        t1.start();

        VolatileExampleThread t2 = new VolatileExampleThread(caller, false);
        t2.start();
    }

}
