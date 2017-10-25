package CH21;

public class BasicThreads {
    public static void main(String[] argv){
        Thread[] t = new Thread[5];
        for(int i=0;i<5;++i){
            t[i] = new Thread(
                    new LiftOff(10)
            );
        }
        for(int i=0;i<5;++i)
            t[i].start();
    }
}

class LiftOff implements Runnable{
    public LiftOff(int countdown){
        this.countdown=countdown;
        id=++count;
    }
    @Override
    public void run(){
        while(countdown-- >0){
            System.out.format("#%d(%d)\n",id,countdown);
            Thread.yield();
        }
        System.out.format("%d lift off!\n",id);
    }

    private int countdown;
    private static int count=0;
    private int id;
}
