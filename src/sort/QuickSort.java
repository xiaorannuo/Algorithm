package sort;

public class QuickSort {
    public static void quicksort(int[] array){
        quicksorthelper(array,0,array.length-1);
    }
    public static void quicksorthelper(int[] array,int low,int high)
    {
        if(low<high)
        {
            int pivotIndex = partition(array,low,high);
            quicksorthelper(array,low,pivotIndex-1);
            quicksorthelper(array,pivotIndex+1,high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivotKey = array[low];
        while (low<high) {
            while (low < high && pivotKey <= array[high]) {
                high--;
            }
            array[low] = array[high];
            while (low<high && pivotKey>array[low]){
                low++;
            }
            array[high] = array[low];
        }
        array[low] = pivotKey;
        return low;
    }

    public static void main(String[] args) {
        int[] array= {9,8,7,6,5,4,3,2,1};
        quicksort(array);
        for (int a:
             array) {
            System.out.println(a);
        }
    }
}
