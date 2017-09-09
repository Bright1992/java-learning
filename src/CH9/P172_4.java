package CH9;

public class P172_4 {
    public static void main(String[] argv) throws Exception{
        Base b=new Derived();
        Derived.getDerived(b).foo();
        b.abstract_foo();

    }
}

abstract class Base implements Printable{
    public abstract void abstract_foo();
}

class Derived extends Base implements Printable{    //父类实现接口后，子类写不写implements都可以
    public static Derived getDerived(Base b) throws Exception{
        return (Derived)b;
    }
    public void print(){

    }
    public void foo(){
        System.out.println("Derived.foo()");
    }
    public void abstract_foo(){
        System.out.println("Derived.abstract_foo()");
    }
}
