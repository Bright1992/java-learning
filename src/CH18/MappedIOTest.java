package CH18;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Random;

public class MappedIOTest {
    private static abstract class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() throws IOException {
            long start = System.nanoTime();
            test();
            long end = System.nanoTime();
            System.out.format("%s: %.3fms\n", name, ((double) end - start) / 1e6);
        }

        public abstract void test() throws IOException;
    }

    private static int numOfInts = 100000;

    private static Tester[] tests = {
            new Tester("Stream Output") {
                @Override
                public void test() throws IOException {
                    File f = new File(".\\MappedIOTestFile");
                    if (!f.exists()) f.mkdirs();
                    f = new File(".\\MappedIOTestFile\\StreamOutput1.dat");
                    DataOutputStream out = new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream(f)
                            )
                    );
                    for (int i = 0; i < numOfInts; ++i)
                        out.writeInt(i);
                    out.close();
                }
            },
            new Tester("Mapped Output") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(".\\MappedIOTestFile\\MappedOutput1.dat","rw");
                    FileChannel fc = raf.getChannel();
                    long n=fc.size();
                    MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,0,numOfInts*4);
                    IntBuffer ib = mbb.asIntBuffer();
                    for(int i=0;i<numOfInts;++i)
                        ib.put(i);
                    fc.close();
                }
            },
            new Tester("Stream Input") {
                @Override
                public void test() throws IOException {
                    DataInputStream in = new DataInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(
                                            new File(".\\MappedIOTestFile\\StreamOutput1.dat")
                                    )
                            )
                    );
                    while(in.available()>0)
                        in.readInt();
                    in.close();
                }
            },
            new Tester("Mapped Input"){
                @Override
                public void test() throws IOException{
                    RandomAccessFile raf = new RandomAccessFile(".\\MappedIOTestFile\\MappedOutput1.dat","r");
                    FileChannel fc = raf.getChannel();
                    MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY,0,fc.size());
                    while(mbb.hasRemaining())
                        mbb.getInt();
                    fc.close();
                }
            }
    };

    public static void main(String[] argv) throws IOException {
        for (Tester t : tests) {
            t.runTest();
        }
    }
}
