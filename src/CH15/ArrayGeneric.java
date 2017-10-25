package CH15;

public class ArrayGeneric<T> {
    public ArrayGeneric(T t){
        this.t=t;
    }
    public T[] a;       //运行时，a的类型被擦除为Object[]
    public T[] f(Class<T> c) throws Exception{
        a=(T[]) new Object[10];     //转型后的实际类型还是Object[]
        for(int i=0;i<10;++i)
            a[i]=c.newInstance();
        return a;
    }

    private T t;
    public T get(){
        return t;
    }

    public static void main(String[] argv) throws Exception{
        ArrayGeneric<String> ag = new ArrayGeneric<>("asdf");
//        String[] s = ag.f(String.class);        //ClassCastException
        Object[] o = ag.f(String.class);
//        String[] s = ag.a;                //ClassCastException
        if(o[0] instanceof String)  System.out.println("true");
//        ag.arrayTest();
        System.out.println(ag.get());
    }

    public String[] s;
    private String[] arrayTest(){
        s = (String[])new Object[1];        //无法进行转型，ClassCastException
        s[0]="ASDF";
        return s;
    }
}
