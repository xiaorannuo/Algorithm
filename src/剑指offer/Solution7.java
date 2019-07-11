package 剑指offer;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution7 {
//    60. n 个骰子的点数
    /*
扔 n 个骰子，向上面的数字之和为 S。给定 n，请列出所有可能的 S 值及其相应的概率。

    Example
    样例 1：

    输入：n = 1
    输出：[[1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]
    解释：掷一次骰子，向上的数字和可能为1,2,3,4,5,6，出现的概率均为 0.17。
    样例 2

    输入：n = 2
    输出：[[2,0.03],[3,0.06],[4,0.08],[5,0.11],[6,0.14],[7,0.17],[8,0.14],[9,0.11],[10,0.08],[11,0.06],[12,0.03]]
    解释：掷两次骰子，向上的数字和可能在[2,12]，出现的概率是不同的。
    */
    
//    61. 扑克牌顺子
//    五张牌，其中大小鬼为癞子，牌面为 0。判断这五张牌是否能组成顺子。
public boolean isContinuous(int [] numbers) {
    if(numbers.length<5)
        return false;
    Arrays.sort(numbers);
    int c = 0;
    int i=0;
    while(numbers[i++]==0)
        c++;
    if(c==0)
    {
        if(numbers[4]-numbers[0]==4)
            return true;
        else
            return false;
    }
    for (int j = c; j <numbers.length-1 ; j++) {
        if(numbers[j+1]==numbers[j])
            return false;
        c-=numbers[j+1]-numbers[j]-1;
    }
    if(c>=0)
        return true;
    else
        return false;
}
//    62. 圆圈中最后剩下的数
//    让小朋友们围成一个大圈。然后，随机指定一个数 m，让编号为 0 的小朋友开始报数。每次喊到 m-1 的那个小朋友要出列唱首歌，
//    然后可以在礼品箱中任意的挑选礼物，并且不再回到圈中，从他的下一个小朋友开始，
//    继续 0...m-1 报数 .... 这样下去 .... 直到剩下最后一个小朋友，可以不用表演
public int LastRemaining_Solution(int n, int m) {
    if(n<1)
        return -1;
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i <n ; i++) {
        list.add(i);
    }

    int id = 0;
    while(list.size()>1)
    {
        for(int i=1;i<m;i++)
        {
            id = (id+1)%list.size();
        }
        list.remove(id);
    }
    return list.get(0);
}

//    63. 股票的最大利润
//    可以有一次买入和一次卖出，买入必须在前。求最大收益。
    public int maxProfit(int[] prices) {
    int profix=0;
    int min = Integer.MAX_VALUE;
    for(int i=0;i<prices.length;i++)
    {
        if(prices[i]<min)
            min = prices[i];
        if((prices[i]-min)>profix)
            profix = (prices[i]-min);
    }
    return profix;
}

//    64. 求 1+2+3+...+n
//    求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
public int Sum_Solution(int n) {
    int sum = n;
    boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
    return sum;
}
//    65. 不用加减乘除做加法
//    写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    public int Add(int num1,int num2) {
        return num2==0?num1:Add(num1^num2,(num1&num2)<<1);
    }

//    66. 构建乘积数组
//    给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
//    其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。

    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[A.length];
        int base = 1;
        B[0] = 1;
        for(int i=1;i<n;i++)
        {
            B[i] = B[i-1]*A[i-1];
        }
        for(int i=n-2;i>=0;i--)
        {
            base = base*A[i+1];
            B[i] = B[i]*base;
        }
        return B;
    }
//    67. 把字符串转换成整数

//    将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
//    要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
    public int StrToInt(String str) {

        if(!str.matches("[+-]?\\d+"))
        {
            return 0;
        }
        int flag = 0;
        int sum = 0;
        char[] ch = str.toCharArray();
        if(ch[0]=='-')
            flag = -1;
        else  if(ch[0]=='+')
            flag = 1;
        for (int i = 0; i < ch.length; i++) {
            if(i==0&&(flag==-1||flag==1))
                continue;
            sum=sum*10+(ch[i]-'0');
        }
        if(flag!=0)
            return sum*flag;
        else
            return sum;
    }
//    68. 树中两个节点的最低公共祖先
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
        //二叉查找树
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root!=null)
        {
            if(root.val==p.val)
                return root;
            if(root.val==q.val)
                return root;
            if(root.val>p.val&&root.val<q.val)
                return root;
            if(root.val<p.val&&root.val>q.val)
                return root;
            if(root.val<p.val&&root.val<q.val)
            {root = root.right;continue;}
            if(root.val>p.val&&root.val>p.val)
            {  root = root.left;continue;}
        }
        return null;
    }
    //普通二叉树

}
