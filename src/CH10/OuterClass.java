package CH10;

import com.sun.javafx.iio.ImageMetadata;

public class OuterClass {
    public class InnerClass{
        public InnerClass(String s){
            Str=s;
        }

        private String getOuterStr(){
            return OuterClass.this.Str;     //对外部类成员的显示引用：OuterClass.this
        }
        private String getInnerStr(){
            return Str;
        }
        private String Str="Inner";
        //! public static int staticMember=0;   //普通内部类不能有static成员
    }

    private class PrivateInnerClass implements Printable{
        public void print(){
            System.out.println("PrivateInnerClass");
        }
        public void foo(){
            System.out.println("foo(): Not available from outside");
        }
    }

    public static class Tester{         //嵌套类
        public static void main(String[] argv){     //嵌套类内可以有外部接口（main）
            System.out.println("static tester");
        }
    }

    public OuterClass(String s){
        Str=s;
    }

    public void classInMethod(String str){
        class InMethodClass{
            public InMethodClass(){
                System.out.println(str+" constructed");
                //! str="modified";     //定义在函数中的内部类同样不能改变外界环境中变量的值
            }
        }
        InMethodClass mc=new InMethodClass();
    }

    public String getInnerStr(InnerClass ic){
        return ic.getInnerStr();
    }
    public String getOuterStr(InnerClass ic){
        return ic.getOuterStr();
    }

    public PrivateInnerClass getPrivate(){
        return new PrivateInnerClass();
    }

    public void privateTest(){
        OuterClass oc = new OuterClass("private");
        System.out.println(oc.Str);     //同一个类的不同对象间可以相互访问私有成员
    }

    private String Str="Outer";
    public static void main(String[] argv){

    }
}
