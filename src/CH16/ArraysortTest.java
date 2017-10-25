package CH16;

import java.util.Arrays;

public class ArraysortTest {
    public static void main(String[] argv){
        int[] a = new int[]{2,4,6,7,9,11};
        System.out.println(Arrays.binarySearch(a,13));      //如果查找对象不存在，返回：-插入点-1
    }
}
