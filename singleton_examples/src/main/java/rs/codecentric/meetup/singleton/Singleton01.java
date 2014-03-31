package rs.codecentric.meetup.singleton;

/**
 * "Classic" singleton.
 */
public class Singleton01 {
    // TODO Make this class final!

    private static Singleton01 instance = null;

    private Singleton01() {
        // Private constructor prevents instantiation, but also prevents sub-classing.
    }

    public static Singleton01 getInstance() {
        if (instance == null) {
            instance = new Singleton01();
        }
        return instance;
    }

}
