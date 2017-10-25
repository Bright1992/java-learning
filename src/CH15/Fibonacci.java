package CH15;

public class Fibonacci implements Generator<Integer> {
    int idx=1;
    public Integer next(){
        return fib(idx++);
    }

    private int fib(int n){
        if(n<1) throw new RuntimeException("index of fibonacci must be positive");
        if(n<=2)    return 1;
        return fib(n-1)+fib(n-2);
    }

    public static void main(String[] argv){
        Generator g = new Fibonacci();
        Generator<Integer> gi = g;

        Fibonacci fib=new Fibonacci();
        for(int i=0;i<10;++i)
            System.out.print(String.format("%d ",fib.next()));
        System.out.println();
    }
}
