package rs.codecentric.meetup.singleton;

public class Singleton06 {

    private static final Singleton06 INSTANCE = new Singleton06();

    private Singleton06() {
        // Do nothing.
    }

    public static Singleton06 getInstance() {
        return INSTANCE;
    }

    // What about serialization/deserialization?

}
