package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 无序数组top K
 */
public class Topk {
    /**
     * 基于patition方法
     * @param arr
     * @param k
     * @return k = length-1-n
     */
    public static List<Integer> topK(int[] arr, int k) {
        if (arr==null||arr.length==0||k>arr.length||k<=0)
            return new ArrayList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int index = QuickSort.partition(arr,0,arr.length-1);
        while (index!=arr.length-1-k)
        {
            if (index<arr.length-1-k)
                index = QuickSort.partition(arr,index+1,arr.length-1);
            else
                index = QuickSort.partition(arr,0,index-1);
        }
        for (int i = index+1; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }
    /*
    *使用一个最小堆
    *
    */

    public static List<Integer> topKByHeap(int[] arr, int k) {
        List<Integer> list = new ArrayList<Integer>();
        PriorityQueue<Integer> pri = new PriorityQueue<Integer>();
        if(k>=arr.length){
            for (int i:
                 arr) {
                list.add(i);
            }
            return list;
        }
        System.out.println("11111");
        int i =0;
        for ( ;i < k; i++) {
            pri.add(arr[i]);
        }
        while (i<arr.length)
        {
            if(pri.peek()<arr[i])
            {
                pri.poll();
                pri.add(arr[i]);
            }
            i++;
        }
        return new ArrayList<>(pri);
    }

    public static void main(String[] args) {
        int[] arr = {1,10,3,4,8,9,7,5,6,2};
        ArrayList<Integer> list = new ArrayList<>(topK(arr,5));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
