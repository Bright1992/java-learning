package CH9.Adapter;

public class Apply {
    public Apply(Processor p){
        processor=p;
    }
    public Object process(Object obj){
        return processor.process(obj);
    }
    public static void main(String[] argv){
        StringAdapter sa = new StringAdapter(new UpperCaseStringInverter());
        Apply a = new Apply(sa);
        System.out.println(a.process("bowen wang"));
    }
    private Processor processor;
}
