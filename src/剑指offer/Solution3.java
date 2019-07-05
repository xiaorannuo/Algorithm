package 剑指offer;

import sun.net.www.MessageHeader;

import java.util.ArrayList;

public class Solution3 {
//    20. 表示数值的字符串
//    请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//    例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
//    但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。

    //    []  ： 字符集合
//    ()  ： 分组
//    ?   ： 重复 0 ~ 1 次
//    +   ： 重复 1 ~ n 次
//    *   ： 重复 0 ~ n 次
//    .   ： 任意字符
//    \\. ： 转义后的 .
//    \\d ： 数字
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    //    21. 调整数组顺序使奇数位于偶数前面
//    输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
//    所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
    public void reOrderArray(int[] array) {
        int[] copy = new int[array.length];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0)
                copy[k++] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0)
                copy[k++] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = copy[i];
        }
    }
//    22. 链表中倒数第 K 个结点
//    输入一个链表，输出该链表中倒数第k个结点。

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode l = head;
        int count = 0;
        while (l != null) {
            l = l.next;
            count++;
        }
        if (count < k || k == 0)//k==0 或者 k比链表长度大
            return null;
        ListNode l1 = head;
        ListNode l2 = head;
        for (int i = 0; i < k - 1; i++) {
            l2 = l2.next;
        }
        while (l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }

    //    23. 链表中环的入口结点
//
//    给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null)
                fast = fast.next.next;
            else
                return null;
            if (slow == fast) {
                fast = pHead;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
//    24. 反转链表
//    输入一个链表，反转链表后，输出新链表的表头。
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/

    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        head.next = null;
        ListNode newhead = ReverseList(next);
        next.next = head;
        return newhead;
    }

    //25.合并两个排序的链表
//    输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    //递归方法
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val > list2.val) {
            list2.next = Merge(list1, list2.next);
            return list2;
        } else {
            list1.next = Merge(list1.next, list2);
            return list1;
        }
    }

    //迭代法
    public ListNode Merge1(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode res = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        if (list1 != null)
            head.next = list1;
        if (list2 != null)
            head.next = list2;
        return res.next;
    }
//    26. 树的子结构
//    输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        if (equaltree(root1, root2))
            return true;
        else return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);

    }

    public boolean equaltree(TreeNode root1, TreeNode root2) {
        if (root2 == null)
            return true;
        if (root1 == null)
            return false;
        if (root1.val != root2.val)
            return false;
        return equaltree(root1.left, root2.left) && equaltree(root1.right, root2.right);
    }

    //    27. 二叉树的镜像
//    操作给定的二叉树，将其变换为源二叉树的镜像。
//    输入描述:
//    二叉树的镜像定义：源二叉树
//    	                8
//                    /  \
//                    6   10
//                    / \  / \
//                    5  7 9 11
//    镜像二叉树
//    	                8
//                    /  \
//                    10   6
//                    / \  / \
//                    11 9 7  5
    public void Mirror(TreeNode root) {
        if (root == null)
            return;
        swap(root);
        Mirror(root.left);
        Mirror(root.right);
    }

    public void swap(TreeNode root) {
        if (root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
//    28 对称的二叉树
//    请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot==null)
            return true;
        return helper(pRoot.left,pRoot.right);
    }
    boolean helper(TreeNode left,TreeNode right)
    {
        if(left==null&&right==null)
            return true;
        if(left==null||right==null)
            return false;
        return left.val==right.val&&helper(left.right,right.left)&&helper(left.left,right.right);
    }
//    29. 顺时针打印矩阵
//    输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
//    例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
//    则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int left = 0;
        int top = 0;
        int right = matrix[0].length-1;
        int down = matrix.length-1;
        while (left<right&&top<down)
        {
            addint(matrix,left,top,right,down,res);
            left++;
            right--;
            top++;
            down--;
        }
        if(left==right&&top==down)
            res.add(matrix[top][left]);
        else if(left==right)
        {
            for(int i=top;i<=down;i++)
            {
                res.add(matrix[i][left]);
            }

        }
        else if(down==top)
        {
            for(int i=left;i<=right;i++)
            {
                res.add(matrix[top][i]);
            }

        }
        return res;
    }
    public void addint(int[][] matrix,int left,int top,int right,int down,ArrayList<Integer> list)
    {
        for (int i = left; i < right; i++) {
            list.add(matrix[top][i]);
        }
        for (int i = top; i < down; i++) {
            list.add(matrix[i][right]);
        }
        for (int i = right; i >left ; i--) {
            list.add(matrix[down][i]);
        }
        for (int i = down; i >top ; i--) {
            list.add(matrix[i][left]);
        }
    }
    //简单方法
    public ArrayList<Integer> printMatrix1(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int r1=0;
        int r2 = matrix.length-1;
        int l1 = 0;
        int l2 = matrix[0].length-1;
        while(r1<=r2&&l1<=l2)
        {
            for(int i=l1;i<=l2;i++)
                list.add(matrix[r1][i]);
            for(int i=r1+1;i<=r2;i++)
                list.add(matrix[i][l2]);
            if(r1!=r2)
                for(int i=l2-1;i>=l1;i--)
                    list.add(matrix[r2][i]);
            if(l1!=l2)
                for(int i=r2-1;i>r1;i--)
                    list.add(matrix[i][l1]);
            r1++;
            r2--;
            l1++;
            l2--;
        }
        return list;
    }
}
