package CH12;

public class ExceptionTest {
    public static void f() throws MyException {
        throw new MyException();
    }

    public static void g() throws MyException {
        MyException me = new MyException("g()", 2);      //new和throw的地点不一样时，printStackTrace会打印new的地方而不是throw的地方
        for (int i = 0; i < 10; ++i) {
            i += 1;
        }
        throw me;
    }

    public static void h() throws MyException {
        try {
            g();
        } catch (MyException me) {
            me.printStackTrace(System.out);
            me.initCause(new RuntimeException());     //返回Throwable
            throw me;   //通过这种方式可以返回MyException
        }
    }

    public static void j() throws Error {
        try {
            g();
        } catch (MyException me) {
            me.printStackTrace(System.out);
            throw new Error(me);        //接受cause参数的构造器，将MyException转换为Error
        }
    }

    public static void main(String[] argv) throws Exception {
        try {
            f();
        } catch (MyException me) {
            me.printStackTrace(System.out); //默认是System.err，输出为红色字体
        }
        try {
            g();
        } catch (MyException me) {
            me.printStackTrace(System.out);
        }
        try {
            h();
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
        try {
            j();
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }
}

class MyException extends Exception {
    public MyException(String msg, int i) {
        super(msg);
        val = i;
    }

    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
    }

    public String getMessage() {
        return super.getMessage() + ":" + val;
    }

    private int val = 0;
}