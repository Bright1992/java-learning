package CH13;

import java.util.Formatter;
import java.util.Scanner;

public class StringIsConst {
    static public class C{
        private int val=0;
        public C(int i){
            val=i;
        }
        public void inc(){
            val++;
        }
        public String toString(){
            return String.valueOf(val);
        }
    }
    static public void process(C c){
        c.inc();
    }
    static public void process(String s){
        if(s==null) return;
        else    s.toLowerCase();    //String的任何方法都会生成一个新的引用，而不会对原来的引用造成影响
    }
    public static void main(String[] argv){
        C c = new C(1);
        String s="JAVA";
        process(c);
        process(s);
        System.out.println(c);
        System.out.println(s);
    }
}

