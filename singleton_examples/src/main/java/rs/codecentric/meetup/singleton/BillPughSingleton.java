package rs.codecentric.meetup.singleton;

public class BillPughSingleton {

    private BillPughSingleton() {
        // Do nothing.
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
