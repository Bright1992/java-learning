package CH9.Adapter;

class StringInverter extends Inverter{
    public StringInverter(){}
    public String invert(String str){
        String s="";
        int l=str.length();
        for(int i=l-1;i>=0;--i){
            s=s+str.charAt(i);
        }
        return s;
    }

    public static void main(String[] argv){
        StringInverter inverter = new StringInverter();
        System.out.println(inverter.invert("bowen wang"));
    }
}

class UpperCaseStringInverter extends StringInverter{
    public UpperCaseStringInverter(){
        super();
    }
    public String invert(String s){
        return super.invert(s).toUpperCase();
    }
}