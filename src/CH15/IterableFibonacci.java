package CH15;

import java.util.Iterator;

public class IterableFibonacci implements Iterable<Integer> {
    private Fibonacci fib = new Fibonacci();
    private int size;
    public IterableFibonacci(int sz){
        size=sz;
    }
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>(){
            public Integer next(){
                size--;
                return fib.next();
            }
            public boolean hasNext(){
                return size>0;
            }
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
    public static void main(String[] argv){
        IterableFibonacci iFib = new IterableFibonacci(10);
        for(int f:iFib)
            System.out.print(String.format("%d ",f));
        System.out.println();
    }
}
