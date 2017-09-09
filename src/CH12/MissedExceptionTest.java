package CH12;

public class MissedExceptionTest {
    public static void main(String[] argv) throws Exception{
        try{
            throw new ImportantException();     //由于在finally中return或throw了，ImportantException丢失了
        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        finally {
            throw new 蛤();
        }
    }
}

class ImportantException extends RuntimeException{

}

class 蛤 extends Exception{
    public String getMessage(){
        return "蛤？？";
    }
}