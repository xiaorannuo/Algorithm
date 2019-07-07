package sort;

public class Patition {


    private static int partition(int[] nums, int l, int h) {
        int i = l, j = h ;
        int v = nums[l];
        while (true) {
            while ( i < h&&nums[i]<v ) i++;
            while ( j > l &&v<nums[j] ) j--;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] array = {6,2,9,3,5,8,1,7};
        System.out.println(partition(array,0,array.length-1));
    }
}
