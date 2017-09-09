package CH13;

import java.nio.CharBuffer;
import java.util.regex.*;
import java.io.*;
import java.util.Scanner;

public class RegexTest {
    public static void main(String[] argv) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader("D:\\College\\G1\\coding\\Java\\src\\CH13\\RegexTest.java"));
        Scanner scanner = new Scanner(input);       //可以用scanner扫描输入流
        StringBuilder sb = new StringBuilder();
        while (input.ready()) {
            sb.append(input.readLine() + "\n");
        }
        Pattern p = Pattern.compile("(?m)[A-Z]\\w*\\b");
        Matcher m = p.matcher(sb.toString());
        int index=0;
        while (m.find()) {
            System.out.println(""+(++index)+": "+m.group());
        }

        index=0;
        StringBuffer sbuf=new StringBuffer();
        m.reset();
        while(m.find()){
            m.appendReplacement(sbuf,m.group().toUpperCase());
            System.out.println(""+(++index)+": "+m.group()+" "+m.start()+" "+m.end());
        }
        m.appendTail(sbuf);
        System.out.println(sbuf);

//        String line = scanner.nextLine();   //input状态变化会影响scanner
//        System.out.println(line);
    }
}
