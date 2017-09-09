package CH10;

public class AnnoClassTest {
    public Printable getPrintable(){
        return new Printable(){
            @Override
            public void print(){
                System.out.println("AnnoClassPrintable");
            }
        };
    }
    public Printable getPrintable(int i){
        return new Printable() {
            @Override
            public void print() {
                System.out.println("AnnoClassPrintable with int: "+i);
                //! ++i;    //外部环境中的变量不能被改变（必须等效为final）
            }
        };
    }
}
