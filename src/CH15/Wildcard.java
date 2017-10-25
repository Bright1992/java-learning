package CH15;

import java.util.ArrayList;
import java.util.List;

public class Wildcard {
    public static void main(String[] argv) {
        extendTest();
        superTest();
    }

    private static void extendTest() {      //确定上界
        List<? extends Fruit> f1 = new ArrayList<>();
        List<? extends Fruit> f2 = new ArrayList<Guava>();  //List<Guava>和List<Apple>都是List<? extends Fruit>的“子类型”，可以赋值
        //f1.add(new Fruit());    //不能向f1和f2中添加任何对象（任何涉及通配符的方法都不能用）

//        Fruit fruit=f1.get(0);      //不能add，但可以get
//        Fruit fruit2=f2.get(0);
//        Apple a = f1.get(0);    //不能保证返回类型匹配（不发生向下转型）（高于等于上界Fruit才安全）
//        Guava g = f2.get(0);    //同理

        //总结：通配符泛型中，泛型类别（T)不能作为传入参数（因为不安全），但能够作为返回参数，但必须返回上限（? extends X 中的 “X"）

        List<Fruit> f3 = new ArrayList<>();
        f3.add(new Apple());
        f3.add(new Fruit());
        List<Apple> f4 = new ArrayList<>();
        f4.add(new Apple());
        //f4.add(new Fruit());    //Fruit不能向下转型

        Wild<? extends Fruit> w = new Wild<>(new Apple());
        Fruit fruit3 = w.get();
        Apple a3 = (Apple) w.get();  //返回? extends Fruit 类型，是可以看做Apple的超类，因此需要向下转型
        System.out.println(w.get());
    }

    private static void superTest(){    //确定下界
        List<? super Apple> al = new ArrayList<>();
        al.add(new Apple());
        al.add(new Fuji());
//        al.add(new Fruit());    //不能确保安全性（不发生向下转型）（低于等于下界Apple才安全）
//        Fruit f = al.get(0);    //同理，不能get
//        Apple a = al.get(0);

        List<Fruit> fl = new ArrayList<>();
        fl.add(new Apple());        //OK
        magic();
    }

    private static void magic(){
        List<Fruit> l=new ArrayList<>();
        Wildcard w = new Wildcard();
        w.add(l,new Apple());       //使用 List<? super Apple> 将 Apple 放入 List<Apple>中
        Fruit f = l.get(0);
        Apple a = (Apple)l.get(0);  //返回Fruit类型，需要向下转型
        System.out.println(f);
        System.out.println(a);
    }

    public void add(List<? super Apple> f, Apple a){
        f.add(a);
    }

}

class Fruit {
    public String toString() {
        return "Fruit";
    }
}

class Apple extends Fruit {
    public String toString() {
        return "Apple";
    }
}

class Fuji extends Apple{
    public String toString(){
        return "FUJI";
    }
}

class Guava extends Fruit {
    public String toString() {
        return "Guava";
    }
}

class Wild<T> {
    private T t;

    public Wild(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}