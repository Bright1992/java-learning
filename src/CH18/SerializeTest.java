package CH18;

import java.util.Random;
import java.io.*;

public class SerializeTest {

    public static void main(String[] argv) throws IOException, ClassNotFoundException {
        Random rnd = new Random();
        int i=rnd.nextInt(10)+1;
        Worm w1 = new Worm(i,(char)('a'+i+rnd.nextInt(10)));
        i=rnd.nextInt(10)+1;
//        Worm w2 = new Worm(i,(char)('a'+i+rnd.nextInt(10)));
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(
                        new File("worm1.out")
                )
        );
        out.writeObject(w1);
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(
                        new File("worm1.out")
                )
        );
        Worm w3 = (Worm)in.readObject();
        System.out.println(w3);
    }

}

class Data implements Serializable{     //所有关联的类都要实现Serializable接口
    public Data(int n){
        this.n=n;
    }
    private int n;
    public String toString(){
        return Integer.toString(n);
    }
}

class Worm implements Serializable{
    public Worm(int i, char c){
        this.c=c;
        this.n=new Data(i);
        System.out.println("Worm "+i+":"+c+" Constructed");
        if(i>1) {
            this.next=new Worm(i-1,(char)(c-1));
        }
    }

    public String toString(){
        String s = "("+n+" ,"+c+")";
        if(next==null)  return s;
        return s+"->"+next.toString();
    }

    private transient Data n;       //transient对象不会被默认序列化
    private char c;
    private Worm next;

    private void writeObject(ObjectOutputStream stream) throws IOException{     //可以实现私有的writeObject和readObject来自定义序列化动作
        stream.defaultWriteObject();        //调用ObjectOutputStream的defaultWriteObject方法执行默认的序列化动作
        stream.writeObject(n);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();     //按序写入，按序读出
        n = (Data)stream.readObject();
    }
}
