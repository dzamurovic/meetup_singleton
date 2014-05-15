package rs.codecentric.meetup.singleton;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

import org.junit.Test;

public class Singleton06HackTest {

    // Break singularity with reflection.
    @Test
    public void testSingularity() throws InterruptedException {
        Singleton06Hack instanceOne = Singleton06Hack.getInstance();
        Singleton06Hack instanceTwo = null;

        try {
            Constructor<?>[] constructors = Singleton06Hack.class.getDeclaredConstructors();
            for (Constructor<?> c : constructors) {
                c.setAccessible(true);
                instanceTwo = (Singleton06Hack) c.newInstance(new Object[] {});
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(instanceOne.hashCode(), instanceTwo.hashCode());
    }

    // Break singularity with serialization.
    @Test
    public void testSerialization() throws FileNotFoundException, IOException, ClassNotFoundException {
        Singleton06Hack instanceOne = Singleton06Hack.getInstance();
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("Singleton06Serializable.ser"));
        out.writeObject(instanceOne);
        out.close();

        ObjectInput in = new ObjectInputStream(new FileInputStream("Singleton06Serializable.ser"));
        Singleton06Hack instanceTwo = (Singleton06Hack) in.readObject();
        in.close();

        assertEquals(instanceOne.hashCode(), instanceTwo.hashCode());
    }

}
