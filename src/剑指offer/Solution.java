package 剑指offer;

import java.util.Stack;

public class Solution {
    //3 数组中重复的数字    *********************
//    在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
//    但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
//    例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
        // Parameters:
        //    numbers:     an array of integers
        //    length:      the length of array numbers
        //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
        //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
        //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
        // Return value:       true if the input is valid, and there are some duplications in the array number
        //                     otherwise false
        public boolean duplicate(int numbers[],int length,int [] duplication) {
            int i=0;
            while(i<length)
            {
                while(numbers[i]!=i)
                {
                    if(numbers[i]==numbers[numbers[i]])
                    {
                        duplication[0] = numbers[i];
                        return true;
                    }else{
                        swap(numbers,i,numbers[i]);
                    }
                }
                i++;
            }
            return false;
        }
        private void swap(int[] nums, int i, int j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }


//5.替换空格        **************
//    请实现一个函数，将一个字符串中的每个空格替换成“%20”。
//    例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
        public String replaceSpace(StringBuffer str) {
            int p1 = str.length()-1;
            for (int i = 0; i <=p1 ; i++) {
                if(str.charAt(i)==' ')
                    str.append("  ");
            }
            int p2 = str.length()-1;
            while (p1>=0&&p2>p1)
            {
                if(str.charAt(p1)==' ')
                {
                    str.setCharAt(p2--,'0');
                    str.setCharAt(p2--,'2');
                    str.setCharAt(p2--,'%');
                }else
                    str.setCharAt(p2--,str.charAt(p1));
                p1--;
            }
            return str.toString();
        }

//    7.重建二叉树   *****
//    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        int pl = 0;
        int pr = pre.length-1;
        int il = 0;
        int ir = in.length-1;
        TreeNode node = helper(pre,in,pl,pr,il,ir);
        return node;
    }
    public TreeNode helper(int [] pre,int [] in,int pl,int pr,int il,int ir){
        if(pl>pr||il>ir)
            return null;
        TreeNode node = new TreeNode(pre[pl]);
        for(int i=il;i<=ir;i++)
        {
            if(pre[pl]==in[i]){
                node.left = helper(pre,in,pl+1,pl-il+i,il,i-1);
                node.right = helper(pre,in,pl-il+i+1,pr,i+1,ir);
                break;
            }
        }
        return node;
    }
    //8. 二叉树的下一个结点 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
// 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;
        TreeLinkNode(int val) {
            this.val = val;
        }
    }
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }
//9.用两个栈实现队列
//    用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }
    public int pop() {
        if(stack2.isEmpty()){
            while (!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}

