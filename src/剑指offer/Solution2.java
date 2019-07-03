package 剑指offer;

public class Solution2 {
//    10.1 斐波那契数列
//    求斐波那契数列的第 n 项，n <= 39。
    int[] array= new int[40];

    public int Fibonacci(int n) {
        if(n==0) {
            array[0] = 0;
            return 0;
        }
        if(n==1) {
            array[1] = 1;
            return 1;
        }
        array[n] = array[n-1]+array[n-2];
        return Fibonacci(n-1)+Fibonacci(n-2);
    }
//    11. 旋转数组的最小数字
//    把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
//    例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0)
            return 0;
        int l = 0,h = array.length-1;
        int mid;
        while(l<h)
        {
            mid = (l+h)/2;
            if(array[mid]==array[l]&&array[mid]==array[h])
            {
                for(int i=l;i<h;i++)
                {
                    if(array[i]>array[i+1])
                        return array[i+1];
                }
                return array[l];
            }
            else if(array[mid]>array[h])
            {
                l = mid+1;
            }else{
                h = mid;
            }
        }
        return array[h];
}
//12. 矩阵中的路径
/*    请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
    每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
    例如 a b c e s f c s a d e e 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，
    但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
    */
    private final static int[][] next = {{0,1},{0,-1},{1,0},{-1,0}};
    private int rows;
    private int cols;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        this.rows = rows;
        this.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(backtracking(buildMatrix(matrix),str,marked,0,i,j))
                    return true;
            }
        }
        return false;
    }
    public boolean backtracking(char[][] matrix,char[] str,
                                boolean[][] marked,int pathlen,int r,int c)
    {
        if(pathlen == str.length)
            return true;
        if(r<0||r>=rows||c<0||c>=cols||marked[r][c]||matrix[r][c]!=str[pathlen])
        {
            return false;
        }
        marked[r][c] = true;
        for (int[] n:next)
        if(backtracking(matrix,str,marked,pathlen+1,r+n[0],c+n[1]))
            return true;
        marked[r][c] = false;
        return false;
    }
    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                matrix[r][c] = array[idx++];
        return matrix;
    }
//15. 二进制中 1 的个数
//    输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
    public int NumberOf1(int n) {
        int count = 0;
        while(n!=0){
            n = n&(n-1);
            count++;
        }
        return count;
    }
//方法2
    private static int NumberOf1_low(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }


//    14. 剪绳子
//    把一根绳子剪成多段，并且使得每段的长度乘积最大
    public int integerBreak(int n) {
            int[] dp = new int[n+1];
            if(n<2)
                return 0;
            if(n==2)
                return 1;
            if(n==3)
                return 2;
            dp[1] = 1;dp[2]=2;dp[3]=3;//到3为止都是不剪最好
            for(int i = 4;i<=n;i++)
            {
                for(int j = 1;j<i;j++)
                {
                    dp[i] = Math.max(dp[i],dp[j]*dp[i-j]);
                }
            }
            return dp[n];
        }
//    16. 数值的整数次方
//    给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 != 0)
            pow = pow * base;
        return isNegative ? 1 / pow : pow;
    }
//    18.2 删除链表中重复的结点
//    在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
//    例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead==null||pHead.next==null)
        {
            return pHead;
        }
        if(pHead.val == pHead.next.val)
        {
            while(pHead.next!=null&&pHead.val==pHead.next.val)
            {
                pHead = pHead.next;
            }
            return deleteDuplication(pHead.next);
        }else{
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }
//    17. 打印从 1 到最大的 n 位数
//    输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999
    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }
    private static void print1ToMaxOfNDigits(char[] number, int digit) {
        if(digit==number.length)
        {
            printdigits(number);
        }else {
            for (int i = 0; i < 10; i++) {
                number[digit] = (char) ('0' + i);
                print1ToMaxOfNDigits(number, digit + 1);
            }
        }
    }
    private static void printdigits(char[] number) {
        int index = 0;
       while(index<number.length&&number[index]=='0') {
                index++;
        }
        for (int i = index; i < number.length; i++) {
            System.out.print(number[i]);
        }

        System.out.println();
    }
//    public static void main(String[] args) {
//        print1ToMaxOfNDigits(3);
//    }

//    13. 机器人的运动范围
//    地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
//    但是不能进入行坐标和列坐标的数位之和大于k的格子。
//    例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
//    但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？


    public int movingCount(int threshold, int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        int count=0;
        boolean[][] marked = new boolean[rows][cols];
        move(threshold,0,0,marked);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols ; j++) {
                if(marked[i][j])
                    count++;
            }
        }
        return count;
    }

    private void move(int threshold, int r, int c, boolean[][] marked) {
        if(r<0||r>=rows||c<0||c>=cols||sum(r,c)>threshold||marked[r][c])
        {
            return ;
        }
        marked[r][c]=true;
        move(threshold,r+1,c,marked);
        move(threshold,r-1,c,marked);
        move(threshold,r,c+1,marked);
        move(threshold,r,c-1,marked);
    }
    private int sum(int r,int c){
        int sum =0;
        while(r!=0)
        {
            sum+=r%10;
            r = r/10;
        }
        while(c!=0)
        {
            sum+=c%10;
            c = c/10;
        }
        return sum;
    }
//19. 正则表达式匹配
//    请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
//    在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配


/*
    具体来说：

    当模式中的第二个字符不是“*”时：


    a1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
    a2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。

    而当模式中的第二个字符是“*”时：
    c1、如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。

    如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
    b1、模式后移2字符，相当于x*被忽略；
    b2、字符串后移1字符，模式后移2字符；
    b3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；
            ---------------------
*/

    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null)
            return false;
        return matchCore(str, 0, pattern, 0);
    }
    private static boolean matchCore(char[] str,int strIndex, char[] pattern,int patternIndex) {
        //如果都匹配到尾了成功//如果模式到尾，字符串没到尾，匹配失败
        if(patternIndex==pattern.length)
            return strIndex==str.length;
        //模式第二个字符是*
        if(patternIndex+1<pattern.length&&pattern[patternIndex+1]=='*'){
            //第一个位置相同 或者 模式第一个位置为. 即任意字符
            // 先保证没有越界，再使用！！！！！！！！！！！！！！
            if((strIndex<str.length&&str[strIndex]==pattern[patternIndex])||(strIndex<str.length&&pattern[patternIndex]=='.')){
                //分三种情况递归
                return matchCore(str,strIndex,pattern,patternIndex+2)//视x*匹配0个字符，即*前一个字符出现0次 情况b1
                        ||matchCore(str, strIndex+1, pattern, patternIndex+2)//匹配1个字符，即*前一个字符仅出现1次 情况b2
                        ||matchCore(str, strIndex+1, pattern, patternIndex);//匹配1个字符，模式保持不变 情况b3
            }else{
                //第一个位置不匹配，模式后移两位  也就是视x*匹配0个字符 情况c1
                return matchCore(str, strIndex, pattern, patternIndex+2);
            }
        }
        //第一个字符匹配，但模式第二个不是* 模式和字符串上都后移一位 情况a1
        if(strIndex<str.length&&(str[strIndex]==pattern[patternIndex]||pattern[patternIndex]=='.')){
            return matchCore(str, strIndex+1, pattern, patternIndex+1);
        }else{
            return false;//第一个字符不匹配并且模式第二个位置不是* 情况a2
        }

    }
}
