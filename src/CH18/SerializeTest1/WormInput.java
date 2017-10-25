package CH18.SerializeTest1;

import java.io.*;

public class WormInput {
    public static void main(String[] argv) throws IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(
                        new File("worm1.out")
                )
        );
        Object worm = in.readObject();
        System.out.println(worm.getClass());
    }
}
