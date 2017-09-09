package CH7;

public class PolyConstructor {
    public static void main(String[] argv){
        Derived2 d = new Derived2();
    }
}

class Base2{
    public Base2(){
        System.out.println("Base2 Constructor");
        foo();  //若派生类被构造，则会执行派生类的foo()，然而此时派生类尚未完成构造，因此foo()中输出的f为0
    }
    protected void foo(){
        System.out.println("foo() from Base2()");
    }
}

class Derived2 extends Base2{
    public Derived2(){
        System.out.println("Derived2 Constructor");
    }
    protected void foo(){
        System.out.println("foo() from Derived, f="+String.valueOf(f));
    }
    private int f=5;
}
