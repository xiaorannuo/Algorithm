package 剑指offer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.ArrayList;

public class Solution4 {

//    30. 包含 min 函数的栈
//    定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();
    public void push(int node) {
        if(stack.isEmpty()){
            min.push(node);
        }else {
            min.add(top()<node?top():node);
        }
            stack.push(node);
    }

    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }

//    31. 栈的压入、弹出序列
//    输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。
//    例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
//    但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<Integer>();
        int i=0;
        int j=0;

        while ( i < pushA.length&&j < popA.length) {
            while (stack.isEmpty()||stack.peek() != popA[j] && i < pushA.length) {
                stack.push(pushA[i++]);
            }
            while (!stack.isEmpty()&&stack.peek() == popA[j] && j < popA.length) {
                stack.pop();
                j++;
            }
        }
        if(i==pushA.length&&j == popA.length&&stack.isEmpty())
            return true;
        else
            return false;

    }
//    32.1 从上往下打印二叉树
//    从上往下打印出二叉树的每个节点，同层节点从左至右打印


     public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;
    
     public TreeNode(int val) {
     this.val = val;
    
     }
     }
     private ArrayList<Integer> res = new ArrayList<Integer>();
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if(root!=null)
            return new ArrayList<Integer>();
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        while(!list.isEmpty())
        {
            ArrayList<TreeNode> copy = new ArrayList<TreeNode>();
            for ( TreeNode node:list) {
                res.add(node.val);
                if(node.left!=null)
                copy.add(node.left);
                if(node.right!=null)
                copy.add(node.right);
            }
            list = copy;
        }
        return res;
    }

//    32.2 把二叉树打印成多行
//    从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
    private ArrayList<ArrayList<Integer> > re = new ArrayList<ArrayList<Integer> >();
        ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
            if(pRoot!=null)
                return new ArrayList<ArrayList<Integer> >();
            ArrayList<TreeNode> list = new ArrayList<TreeNode>();
            list.add(pRoot);
            while(!list.isEmpty())
            {
                ArrayList<TreeNode> copy = new ArrayList<TreeNode>();
                ArrayList<Integer> a = new ArrayList<Integer>();
                for ( TreeNode node:list) {
                    a.add(node.val);
                    if(node.left!=null)
                        copy.add(node.left);
                    if(node.right!=null)
                        copy.add(node.right);
                }
                list = copy;
                re.add(a);
            }
            return re;
        }
//    32.3 按之字形顺序打印二叉树
//    请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
//    第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。

    public ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot) {
            if(pRoot==null)
                return new ArrayList<ArrayList<Integer> >();
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(pRoot);
            int i=0;
            while(!stack.isEmpty())
            {
                Stack<TreeNode> copy = new Stack<TreeNode>();
                ArrayList<Integer> list = new ArrayList<Integer>();
                    while (!stack.isEmpty()) {
                        TreeNode node = stack.pop();
                        list.add(node.val);
                        if(i%2!=0) {
                            if (node.right != null)
                                copy.push(node.right);
                            if (node.left != null)
                                copy.push(node.left);
                        }else {
                            if (node.left != null)
                                copy.push(node.left);
                            if (node.right != null)
                                copy.push(node.right);
                        }
                    }
                    re.add(list);
                i++;
                stack = copy;
            }
            return re;
    }
//    33. 二叉搜索树的后序遍历序列
//    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
//    如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0||sequence==null)
            return false;
        int l = 0;
        int r = sequence.length-1;
        return isbst(sequence,l,r);
    }

    private boolean isbst(int[] sequence, int l, int r) {
            if(l>=r)
                return true;
            int rr = r-1;
            int copy=rr;
        while(rr>=l)
        {
            if(sequence[rr]<sequence[r])
                break;
            rr--;
        }
        copy = rr;
        while (rr>=l){
            if(sequence[rr]>sequence[r])
                return false;
            rr--;
        }
        return isbst(sequence,l,copy)&&isbst(sequence,copy+1,r-1);
    }
//    34. 二叉树中和为某一值的路径
//    输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
//    路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {

        if (root==null)
            return new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        findpathhelper(list,root,target);
        return re;
    }

    private void findpathhelper(ArrayList<Integer> list, TreeNode root, int target) {
            if(root.val>target)
                return;
            if (root.val==target&&root.left==null&&root.left==null)
            {
                list.add(root.val);
                re.add(new ArrayList<Integer>(list));
                list.remove(list.size()-1);
            }
            list.add(root.val);
            if(root.left!=null)
            findpathhelper(list,root.left,target-root.val);
            if(root.right!=null)
            findpathhelper(list,root.right,target-root.val);
            list.remove(list.size()-1);
    }
//    35. 复杂链表的复制
//    输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
//    返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
    public RandomListNode Clone(RandomListNode pHead)
    {
        HashMap<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
        RandomListNode head = pHead;
        while(pHead!=null)
        {
            map.put(pHead,new RandomListNode(pHead.label));
            pHead = pHead.next;
        }
        RandomListNode n = map.get(head);
        RandomListNode res =  n;
        while (head!=null)
        {
            n.next = map.get(head.next);
            n.random = map.get(head.random);
                    head = head.next;
                    n = n.next;
        }
        return res;
    }
//    36. 二叉搜索树与双向链表
//    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null)
            return null;
        TreeNode res = pRootOfTree;
        if(pRootOfTree.left!=null) {
            TreeNode left  = Convert (pRootOfTree.left);
            TreeNode move = left;
            while(move.right!=null)
            {
                move = move.right;
            }
            move.right = pRootOfTree;
            pRootOfTree.left = move;
            res = left;
        }
        if(pRootOfTree.right!=null)
        {
            TreeNode right  = Convert (pRootOfTree.right);
            pRootOfTree.right = right;
            right.left = pRootOfTree;
        }
        return res;
    }
//    37. 序列化二叉树    *****做的不好
//    请实现两个函数，分别用来序列化和反序列化二叉树
int index = -1;
    String Serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if(root==null)
            sb.append("#!");
        else{
            sb.append(root.val).append("!");
            sb.append(Serialize(root.left));
            sb.append(Serialize(root.right));
        }
        return sb.toString();
    }
    TreeNode Deserialize(String str) {
        index++;
        if(index>=str.length())
            return null;
        String[] strr = str.split("!");
        TreeNode node = null;
        if(!strr[index].equals("#"))
        {
            node = new TreeNode(Integer.valueOf(strr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }
//    38. 字符串的排列
//    输入一个字符串,按字典序打印出该字符串中字符的所有排列。
//    例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
//    输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母
    ArrayList<String> strings = new ArrayList<String>();
    public ArrayList<String> Permutation(String str) {
        if(str.length()==0)
            return strings;
        char[] cs  = str.toCharArray();
        Arrays.sort(cs);
        backtracking(cs,new boolean[cs.length],new StringBuilder());
        return  strings;
    }

    private void backtracking(char[] cs, boolean[] booleans, StringBuilder stringBuilder) {
        if(stringBuilder.length()==cs.length) {
            strings.add(stringBuilder.toString());
            return;
        }
        for (int i = 0; i < cs.length; i++) {
            if(booleans[i])
                continue;
            if(i!=0&&cs[i]==cs[i-1]&&!booleans[i-1])
                continue;
            stringBuilder.append(cs[i]);
            booleans[i] = true;
            backtracking(cs,booleans,stringBuilder);
            booleans[i] = false;
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }
//    39. 数组中出现次数超过一半的数字
//    数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//    例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
//    如果不存在则输出0。
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length==0)
            return 0;
        int most = array[0];
        int sum=0;
        int count=1;
        for (int i = 1; i < array.length; i++) {
            if(array[i]==most)
                count++;
            else count--;
            if(count==0)
            {
                most = array[i];
                count=1;
            }
        }
        for (int i:
            array ) {
            if(i==most)
                sum++;
        }
        return sum>array.length/2 ? most:0;
    }

}
