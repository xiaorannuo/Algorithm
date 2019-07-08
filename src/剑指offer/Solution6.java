package 剑指offer;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Solution6 {
//    50. 第一个只出现一次的字符位置
//在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
//    如果没有则返回 -1（需要区分大小写
    public int FirstNotRepeatingChar(String str) {
        int[] arr = new int[256];
        char[] ch = str.toCharArray();
        for (char c:ch
             ) {
            arr[c]+=1;
        }
        for (int i = 0; i <ch.length ; i++) {
            if(arr[ch[i]]==1)
                return i;
        }
        return -1;
    }
    public int FirstNotRepeatingChar1(String str) {
        BitSet b1 = new BitSet(256);
        BitSet b2 = new BitSet(256);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(!b1.get(chars[i])&&!b2.get(chars[i]))
            {
                b2.set(chars[i]);
            }else if (!b1.get(chars[i])&&b2.get(chars[i]))
                b1.set(chars[i]);
        }
        for (int i = 0; i <chars.length ; i++) {
            if(!b1.get(chars[i])&&b2.get(chars[i]))
                return i;
        }
        return -1;
    }
//51. 数组中的逆序对
//    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
//    输入一个数组,求出这个数组中的逆序对的总数P。
//    并将P对1000000007取模的结果输出。 即输出P%1000000007
int count = 0;
    public int InversePairs(int [] array) {
        inverse(array,0,array.length-1);
        return count%1000000007;
    }

    private void inverse(int[] array, int i, int i1) {
        if(i>=i1)
            return;
        int mid = (i+i1)/2;
        inverse(array,i,mid);
        inverse(array,mid+1,i1);
        merge(array,i,mid,i1);
    }

    private void merge(int[] array, int i,int mid, int i1) {
        int m=i;
        int n=mid+1;
        int l=0;
        int[] copy = new int[i1-i+1];

        while(m<=mid&&n<=i1)
        {
            if(array[m]<array[n])
            {
                copy[l++] = array[m++];
            }else {
                copy[l++] = array[n++];
                count+=mid-m+1;//算逆序对的时候比较关键
                count=count%1000000007;
            }
        }
        while (m<=mid)
            copy[l++] = array[m++];
        while (n<=i1)
            copy[l++] = array[n++];
        for (int j = i,k=0; j <=i1 ; j++,k++)
            array[j] = copy[k];
    }

    //    52. 两个链表的第一个公共结点
     class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    HashSet<ListNode> set = new HashSet<ListNode>();
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null)
            return null;
        while (pHead1!=null)
        {
            set.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2!=null)
        {
            if(set.contains(pHead2))
                return pHead2;
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；同样地，当访问链表 B 的指针访问到链表尾部时，
     * 令它从链表 A 的头部重新开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }
//    53. 数字在排序数组中出现的次数   看到排好序想到二分法 注意边界值

//    统计一个数字在排序数组中出现的次数。
public int GetNumberOfK(int[] nums, int K) {
    int first = binarySearch(nums, K);
    int last = binarySearch(nums, K + 1);
    return (first == nums.length || nums[first] != K) ? 0 : last - first;
}

    private int binarySearch(int[] nums, int K) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= K)
                h = m;
            else
                l = m + 1;
        }
        return l;
    }

//    54. 二叉查找树的第 K 个结点
//    给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）
//    中，按结点数值大小顺序第三小结点的值为4。
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
ArrayList<TreeNode> list = new ArrayList<TreeNode>();
    //int count=0;
    TreeNode node;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
//        midpath(pRoot);
//        if(k>list.size()||k<1)
//            return null;
//        return list.get(k-1);
        midpath(pRoot,k);
        return node;
    }

    private void midpath(TreeNode pRoot,int k) {
        if(pRoot==null)return;
        if(pRoot.left!=null)
            midpath(pRoot.left,k);
        count++;
        if(count==k)
            node = pRoot;
        if(pRoot.right!=null)
            midpath(pRoot.right,k);
    }

//    55.1 二叉树的深度
    public int TreeDepth(TreeNode root) {
        if(root==null)return 0;
        return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
    }
//    55.2 平衡二叉树
    public boolean IsBalanced_Solution(TreeNode root) {
        return depth(root)!=-1;
    }
    public int depth(TreeNode root)
    {
        if(root==null)
            return 0;
        int num1 = depth(root.left);
        int num2 = depth(root.right);
        if(num1==-1||num2==-1)
            return -1;
        else if (Math.abs(num1-num2)>1)
            return -1;
        else return Math.max(num1,num2)+1;
    }
//    56. 数组中只出现一次的数字
//    一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字
    //注意&和==等优先级
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int n = 0;
        int flag = 1;
        for(int i=0;i<array.length;i++)
            n = n^array[i];
        while((flag&n)==0)
        {
            flag = flag<<1;
        }
        for(int i=0;i<array.length;i++)
        {
            if((array[i]&flag)==0)
                num1[0]^=array[i];
            else
                num2[0]^=array[i];
        }
    }
//    57.2 和为 S 的连续正数序列
    /*
    * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
    * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,
    * 他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列?
    * Good Luck!
输出描述:
输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序*/

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<ArrayList<Integer> >();

        int start = 1;
        int end = 2;
        int s = 3;
        while(end<sum)
        {
            if(s==sum){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int i=start;i<=end;i++)
                    list.add(i);
                res.add(list);
                end++;
                s=s-start+end;
                start++;
            }else if(s>sum){
                s -=start++;
            }else{
                s+=(++end);
            }
        }
        return res;
    }
//    58.1 翻转单词顺序列
//    牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
//    同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
//    例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
//    正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
    public String ReverseSentence(String str) {
        char[] chars = str.toCharArray();
        int i=0;
        int j=chars.length-1;
        while(i<j)
        {
            swap(chars,i,j);
            i++;
            j--;
        }
        i=0;
        j=0;
        while (j<chars.length) {
            while (j<chars.length&&chars[j] != ' ')
                j++;
            swa(chars,i,j-1);
            i = j+1;
            j++;
        }
        return new String(chars);
    }
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    private void swa(char[] chars,int i,int j)
    {
        while(i<j)
        {
            swap(chars,i,j);
            i++;
            j--;
        }
    }
//    58.2 左旋转字符串
//    汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
//    对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
//    例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
public String LeftRotateString(String str,int n) {
    return n>str.length()?"":str.substring(n)+str.substring(0,n);

}
//59. 滑动窗口的最大值
//    给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
//    那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
//    {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
//    {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {

        ArrayList<Integer> list =  new ArrayList<Integer>();
        if(num.length<size||size<1)
            return list;
        PriorityQueue<Integer> big = new PriorityQueue<Integer>((o1, o2) -> o2-o1);
        for (int i = 0; i < size; i++) {
            big.add(num[i]);
        }
        list.add(big.peek());
        for (int i=size,j=0;i<num.length;i++,j++)
        {
            big.remove(num[j]);
            big.add(num[i]);
            list.add(big.peek());
        }
        return list;
    }
}


