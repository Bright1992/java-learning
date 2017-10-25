package CH11;

import com.sun.xml.internal.ws.api.server.Adapter;

import javax.naming.LinkLoopException;
import java.util.*;

public class ContainerTest {
    public static void main(String[] argv) {
        List<Integer> al = new ArrayList<Integer>();
        List<Integer> ll = new LinkedList<Integer>();
        Collections.addAll(al, 1, 2, 3, 4);
        Integer[] array=new Integer[]{1,2,3,4};
        int ai[][]=new int[10][];
        char c='1';
        if(c>'0')   System.out.println('c');
        //两种addAll
        al.addAll(Arrays.asList(array));
        ll = new LinkedList<Integer>(Arrays.<Integer>asList(1, 4));  //显示类型参数说明
//        Arrays.asList(1,4).removeAll(Arrays.asList(1));     //UnsupportedOperation
        System.out.println(al);
        al=Arrays.asList(array);        //1. 返回类型不是ArrayList。2. 对al的改变会影响array
        Collections.shuffle(al);
        System.out.println("ArrayList: "+al);
        System.out.println("Array: "+Arrays.toString(array));   //如果直接
        al=new ArrayList<Integer>(Arrays.asList(array));
        Collections.sort(al);
        System.out.println(al);
        al.retainAll(ll);
        System.out.println(al);
        Iterator<Integer> it = al.iterator();
        while(it.hasNext())
            System.out.format("%d\n",it.next());
        ListIterator<Integer> lit = al.listIterator();
        LinkedList<Integer> lll = (LinkedList<Integer>) ll;
        System.out.println(lll.poll()); //=queue.pop()
        System.out.println(lll.peek()); //=queue.front(), stack.front()

        lll.peekLast(); //=queue.back()
        lll.add(5); //=queue.push_back(), addLast()
        lll.pop();  //=stack.pop()
        lll.push(6);    //=stack.push(), addFirst()
        lll.add(7);
        lll.offer(8);   //=add()
        lll.pop();
        lll.pollLast();
        System.out.println(lll);

        Stack<Integer> stack=new Stack<Integer>();  //Stack是单独的类，已过时！
        Queue<Integer> queue=lll;   //LinkedList可以向上转型为Queue
        Queue<Integer> pq = new PriorityQueue<Integer>();   //PriorityQueue是Queue的子类

        Random r = new Random(57);
        Set<Integer> hs = new HashSet<Integer>(Arrays.<Integer>asList(4,6,3,7,8,1,0,4));   //无顺序
        Set<Integer> ts = new TreeSet<Integer>(hs);   //有顺序（默认排序）
        Set<Integer> lhs = new LinkedHashSet<Integer>(hs);    //使用链表维护了插入顺序
        lhs.add(-1);
        SortedSet<Integer> ss= (TreeSet<Integer>)ts;    //SortedSet是TreeSet和LinkedHashSet的接口
        System.out.println(lhs);

        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        Map<Integer, Integer> tm = new TreeMap<Integer, Integer>();
        Map<Integer, Integer> lhm = new LinkedHashMap<Integer, Integer>();
        for(int i=0;i<1000;++i) {
            int d=r.nextInt(10);
            hm.put(d,hm.getOrDefault(d,0)+1);
        }
        tm.putAll(hm);
        lhm.putAll(hm);
        System.out.println("HashMap: "+hm);
        //Map is not Iterable
        for(Map.Entry entry:hm.entrySet()){
            System.out.println(entry);
        }

        List<List<Integer>> lol=new LinkedList<>();
        LinkedList<Integer> il=new LinkedList<>();
        il.add(1);
        lol.add((List)il.clone());
        il.set(0,0);
        System.out.println(lol.get(0).get(0));
        String s="asdf";
        StringBuilder sb = new StringBuilder();
    }
}
