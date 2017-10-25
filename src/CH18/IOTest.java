package CH18;

import java.io.*;
import java.util.Scanner;
import java.util.TreeSet;

public class IOTest {
    public static void main(String[] argv) throws IOException {
        test();
        memoryRead("D:\\College\\G1\\coding\\Java\\src\\CH13\\RegexTest.java");
        formattedRead("D:\\College\\G1\\coding\\Java\\src\\CH13\\RegexTest.java");
        copy("D:\\College\\G1\\coding\\Java\\src\\CH13\\RegexTest.java","D:\\College\\G1\\coding\\RegexTest.java");
    }

    public static String read(String filename) throws IOException {
        File f = new File(filename);
        if(!f.exists()) throw new IOException("File not exist!");
        BufferedReader br = new BufferedReader(
                new FileReader(f)
        );
        String s;
        StringBuffer sb = new StringBuffer();
        StringBuilder sb2 = new StringBuilder();    //StringBuilder比StringBuffer快，但StringBuffer是线程安全的
        while((s=br.readLine())!=null){
            sb.append(s+'\n');
        }
        br.close();
        return sb.toString();
    }

    public static void memoryRead(String filename) throws IOException{
        StringReader sb = new StringReader(
                read(filename)
        );
        int c;
        while((c=sb.read())!=-1){
            System.out.print((char)c);
        }
        sb.close();
    }

    public static void formattedRead(String filename) throws IOException{
        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(       //读取字节流，必须给定字节数组
                        read(filename).getBytes()
                )
        );
        while(in.available()>0){        //对于非文件的IO，IO阻塞时available会为0，虽然可能后续还有数据
            System.out.print((char)in.readByte());  //没有提供unicode支持，中文是乱码
        }
        in.close();
    }

    public static void copy(String srcFilename, String dstFilename) throws IOException{
        File srcFile = new File(srcFilename);
        if(!srcFile.exists())   throw new IOException("File not exist");
        File dstFile = new File(dstFilename);
        BufferedReader src = new BufferedReader(
                new FileReader(srcFile)
        );
        PrintWriter dst = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(dstFile)
                )
        );
        //也可以这样：
//        PrintWriter dst = new PrintWriter(dst);
        String s;
        while((s=src.readLine())!=null)
            dst.write(s+'\n');
        src.close();
        dst.close();
    }



    private static void test() throws IOException {
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(
                        new File(".\\IOTest.dat")
                )
        );
        PrintStream ps = new PrintStream(
                new FileOutputStream(
                        new File(".\\IOTest.dat")
                )
        );
//        dos.writeInt(111);
        dos.writeLong(0xFFFFFFFF);
        dos.writeChars("asdfasdfasdfasdf");
        ps.print("范德萨asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf阿斯蒂芬");       //不同的流写入相同文件会相互覆盖
        DataInputStream dis = new DataInputStream(
                new FileInputStream(
                        new File(".\\IOTest.dat")
                )
        );
        System.out.println(dis.readInt());
        System.out.println(dis.readChar());     //Char类型占2字节
        System.out.println((char) dis.readByte());  //读取单字节字符
        System.out.println(dis.readFloat());
        RandomAccessFile raf = new RandomAccessFile(".\\IOTest.dat","rw");
//        raf.writeChars("12345678");
//        raf.seek(5);
//        raf.writeChar('’');
        raf.close();
        System.out.println(dis.readInt());
        System.out.println(dis.readChar());
        System.out.println(dis.readByte());
        System.out.println(dis.readFloat());
//        dis.close();
        ps.close();
//        dos.close();

        Reader reader = new InputStreamReader(dis);    //适配器
//        Reader r = new Reader();    //Reader是抽象类
        Writer writer = new OutputStreamWriter(ps);
//        FileReader fr = new FileReader(reader);     //FileReader是被装饰对象
        writer.close();
        FileReader fr = new FileReader(".\\IOTest.dat");
        char[] cbuf = new char[100];
        fr.read(cbuf);          //提供了Unicode支持，所以能够自动把单字节的ascii字符放入双字节的char变量中
        System.out.println(cbuf[0]);
        System.out.println((char)((cbuf[4]<<8)+cbuf[5]));
        System.out.println(cbuf);
        BufferedReader br = new BufferedReader(reader);     //BufferedReader是装饰组件
        System.out.println(br.readLine());
        br.close();
        dis.close();
        dos.close();
        reader.close();
        fr.close();
    }
}
