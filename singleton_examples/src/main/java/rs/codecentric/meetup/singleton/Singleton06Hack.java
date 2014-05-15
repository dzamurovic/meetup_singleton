package rs.codecentric.meetup.singleton;

import java.io.Serializable;

public class Singleton06Hack implements Serializable {

    private static final long serialVersionUID = 8382313507182286121L;

    private static final Singleton06Hack INSTANCE = new Singleton06Hack();

    private Singleton06Hack() {
        // Do nothing.
    }

    public static Singleton06Hack getInstance() {
        return INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

}
