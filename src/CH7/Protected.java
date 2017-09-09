package CH7;

public class Protected {
    public static void main(String[] argv){
        Base b=new Base();
        int [][] a = new int[10][]; //可变数组
        b.foo();    //protected 有包访问权限
//        b.foo2();   //private 没有包访问权限
    }
}

class Base{
    protected void foo(){
        System.out.println("protected");
    }
    private void foo2(){
        System.out.println("private");
    }
}