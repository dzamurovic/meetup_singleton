package meetup;

import static org.junit.Assert.*;

import org.junit.Test;

public class Singleton01Test {

    @Test
    public void test() {
        Singleton01 a = Singleton01.getInstance();
        Singleton01 b = Singleton01.getInstance();
        assertTrue(a.equals(b));
    }

}
