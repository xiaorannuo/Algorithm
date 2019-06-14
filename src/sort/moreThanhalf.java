package sort;

/**
 * 数组中出现超过一半的数
 */
public class moreThanhalf {
    public static void main(String[] args) {
        int[] arr= {1,5,3,4,5,6,5,5,2};
        System.out.println(moreThanhalf(arr));
    }
    public static int moreThanhalf(int[] arr){
        if(arr==null||arr.length==0)
            return 0;
        int low = 0;
        int high = arr.length-1;
        int index = QuickSort.partition(arr,low,high);
        int mid = (low+high)/2;
        while (index!=mid){
            if(index>mid)
            {
                index = QuickSort.partition(arr,low,index-1);
            }else
                index = QuickSort.partition(arr,index+1,high);
        }
        if(!checkMoreThanHalf(arr,index))
            return 0;
        return arr[index];
    }

    /**
     * 检验是否真的超过了一半
     * @param arr
     * @param index
     * @return
     */
    private static boolean checkMoreThanHalf(int[] arr, int index) {
        int num = 0;
        for (int a:arr
             ) {

            if(a==arr[index])
                num++;
        }
        return num*2>arr.length;
    }
}
