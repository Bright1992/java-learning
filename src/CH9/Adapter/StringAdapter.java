package CH9.Adapter;

public class StringAdapter implements Processor {
    public StringAdapter(Inverter i){
        inverter=i;
    }

    @Override
    public String process(Object obj) { //协变类型
        return inverter.invert((String)obj);
    }

    private Inverter inverter;

    public static void main(String[] argv){
        Inverter inverter = new UpperCaseStringInverter();
        StringAdapter adapter = new StringAdapter(inverter);
        System.out.println(adapter.process("bowen wang"));
    }
}