package CH13;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader(
                        new File(filename).getAbsoluteFile()
                )
        );
        StringBuilder sb = new StringBuilder();
        String s;
        while((s=in.readLine())!=null)
            sb.append(s+'\n');
        in.close();
        return sb.toString();
    }

    public static void write(String filename, String text) throws IOException{
        PrintWriter out = new PrintWriter(filename);
        out.print(text);
        out.close();
    }

    public TextFile(String filename, String delimiter) throws IOException{
        super(Arrays.asList(read(filename).split(delimiter)));
        this.delimeter = delimiter;
    }

    private static void nioTest() throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        bb.asCharBuffer().put("asdfasdfasdfasdfasdfasdfaasdf");
        FileChannel fc = new FileOutputStream(new File("nioTest.txt")).getChannel();
        System.out.println(Charset.forName(System.getProperty("file.encoding")).decode(bb));
        bb.flip();  //limit设为position，position设为0. 所以第一次读之前不要flip
        fc.write(bb);
        fc.close();
    }

    public TextFile(String filename) throws IOException{
        this(filename,"\n");        //default delimiter is '\n'
    }

    public void writeWithDelimiter(String filename, String delimiter) throws IOException{
        PrintWriter out = new PrintWriter(filename);
        for(int i=0;i<this.size()-1;++i){
            out.print(this.get(i)+delimiter);
        }
        out.print(this.get(this.size()-1));
        out.close();
    }

    public void write(String filename) throws IOException{
        this.writeWithDelimiter(filename,"\n");
    }

    private String delimeter;

    public static void main(String[] argv) throws IOException {
        write("TextFileTest.txt",read("D:\\College\\G1\\coding\\Java\\src\\CH13\\RegexTest.java"));
        TextFile tf = new TextFile("TextFileTest.txt","\\W+");
        TreeSet<String> t = new TreeSet<>(tf);
        System.out.println(t);
        tf.writeWithDelimiter("TextFileTest2.txt","\n");
        nioTest();
    }
}
