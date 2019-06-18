package sort;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 数据流中的中位数
 */
public class MidInStream {
    static PriorityQueue<Integer> minheap = new PriorityQueue<Integer>();
    static PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });
    public static void insert(int num){
        if (minheap.size()==0)
            minheap.add(num);
        else if(minheap.peek()<num) {
            minheap.add(num);
        }else {
            maxheap.add(num);
        }
        if(Math.abs(minheap.size()-maxheap.size())>1)
        {
            if(minheap.size()-maxheap.size()>1)
                maxheap.add(minheap.poll());
            else
                minheap.add(maxheap.poll());
        }
    }
    public static double getmid(){
        if(minheap.size()==maxheap.size())
        {
            return (maxheap.peek()+minheap.peek())/2.0;
        }else if(minheap.size()<maxheap.size())
        {
            return maxheap.peek()*1.0;
        }else
            return minheap.peek()*1.0;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        for (int i = 0; i <arr.length ; i++) {
            insert(arr[i]);
            System.out.println(getmid());
        }
    }
    /**
     * 第二种方法
     * 把所有的数字分为两部分，数据总数为偶数时，新加入的元素与大根堆中所有元素比较，最 大值进入小根堆
     * * 数据总数为奇数时，新加入的元素与小根堆中所有元素比较，最小值进入大根堆
     * * 结果就是:大根堆存放较小的一部分数字，小根堆存放较大的一部分数字
     * * 当数据总数为偶数时，中位数为(大根堆顶+小根堆顶)/2
     * * 当数据总数为奇数时，小根堆总数比大根堆多一个，中位数即为小根顶
     */
//    public void insert(int num) {
//      if ((count & 1) == 0) {
//        maxHeap.add(num);
//        minHeap.add(maxHeap.poll()); }
//        else{
//        minHeap.add(num);
//        maxHeap.add(minHeap.poll()); }
//        count++; }
//    public double getMedian() { if((count & 1) == 0){
//        return 1.0 * (minHeap.peek() + maxHeap.peek()) / 2; }
//        else{
//        return 1.0 * minHeap.peek(); }
//    }
}
