package CH10;

public class test {
    public static void main(String[] argv){
        OuterClass oc=new OuterClass("oc");
        OuterClass.InnerClass ic = oc.new InnerClass("ic");     //OuterClass.Innerclass innerObject = outerObject.new InnerClass()
        System.out.println(oc.getInnerStr(ic));
        System.out.println(oc.getOuterStr(ic));
        OuterClass oc2=new OuterClass("oc2");
        System.out.println(oc2.getInnerStr(ic));
        System.out.println(oc2.getOuterStr(ic));        //每一个（非静态）内部类对象都与唯一的外部类对象绑定
        oc.classInMethod("class in method");

        //! Printable p = oc.new PrivateInnerClass();                       //不能从外部得到访问私有内部类的名称
        Printable p = oc.getPrivate();                  //向上转型
        p.print();

        AnnoClassTest ac=new AnnoClassTest();
        ac.getPrintable().print();
        ac.getPrintable(1).print();
    }
}
