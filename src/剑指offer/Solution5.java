package 剑指offer;

import java.util.*;

public class Solution5 {

//    40. 最小的 K 个数
//    输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        if(k>input.length)
            return array;

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i:input
             ) {
            pq.add(i);
        }
        for (int i = 0; i < k; i++) {
            array.add(pq.poll());
        }
        return array;
    }
//    41.1 数据流中的中位数
//    如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
//    如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
//    我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
    PriorityQueue<Integer> maxhead = new PriorityQueue<Integer>(new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
});
    PriorityQueue<Integer> minhead = new PriorityQueue<Integer>();
    public void Insert(Integer num) {
        if(minhead.isEmpty())
            minhead.add(num);
        else if(num>minhead.peek())
            minhead.add(num);
        else
            maxhead.add(num);
        if(minhead.size()-maxhead.size()>1)
        {
            maxhead.add(minhead.poll());
        }
        if(maxhead.size()-minhead.size()>1)
        {
            minhead.add(maxhead.poll());
        }

    }

    public Double GetMedian() {
        if(minhead.size()>maxhead.size())
            return (double)minhead.peek();
        else if(maxhead.size()>minhead.size())
            return (double)maxhead.peek();
        return (minhead.peek()+maxhead.peek())/2.0;
    }
//    41.2 字符流中第一个不重复的字符
//    请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
//    第一个只出现一次的字符是"g"。
//    当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
HashMap<Character,Integer> map = new HashMap<Character, Integer>();
    ArrayList<Character> list = new ArrayList<Character>();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        list.add(ch);
        if(map.containsKey(ch))
        {
            map.put(ch,map.get(ch)+1);
        }else
            map.put(ch,1);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {

        for (char a:
             list) {
            if(map.get(a)==1)
                return a;
        }
        return '#';
    }
//    42. 连续子数组的最大和
//    HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,
//    常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
//    并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
//    给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] sum = new int[array.length];
        int max = Integer.MIN_VALUE;
        sum[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sum[i] = Math.max(sum[i-1]+array[i],array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            if(sum[i]>max)
            max = sum[i];
        }
        return max ;
    }
//    43. 从 1 到 n 整数中 1 出现的次数
//    https://blog.csdn.net/u013132035/article/details/80768636
//    求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
//    但是对于后面问题他就没辙了。
//    ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
    public static int NumberOf1Between1AndN_Solution(int n) {
        if(n==0)
            return 0;
        int count = 0;
        int i;
        for( i=10;i<=n;i = i*10)
        {
            int a = n/i;int b = n%i;
            count+=a*(i/10);
            if(b/(i/10)>1)
                count+=i/10;
            else if(b/(i/10)==1)
                count+=b%(i/10)+1;
        }
        if(n/(i/10)==1)
            count+=n%(i/10)+1;
        else
            count+=i/10;
        return count;
    }
//    44. 数字序列中的某一位数字
//    数字以 0123456789101112131415... 的格式序列化到一个字符串中，求这个字符串的第 index 位。


//    45. 把数组排成最小的数
//    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//    例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

    public static String PrintMinNumber(int [] numbers) {
       String str="";
       String[] ss = new String[numbers.length];
        for (int i = 0; i < numbers.length ; i++) {
            ss[i] = numbers[i]+"";
        }
        Arrays.sort(ss, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });
        for (String s:ss
             ) {
            str+=s;
        }
       return str;
    }

//46. 把数字翻译成字符串
//A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
//            'A' -> 1
//            'B' -> 2
//            ...
//            'Z' -> 26
//    Given a non-empty string containing only digits, determine the total number of ways to decode it.
//
//            Example 1:
//
//    Input: "12"
//    Output: 2
//    Explanation: It could be decoded as "AB" (1 2) or "L" (12).
//    Example 2:
//
//    Input: "226"
//    Output: 3
//    Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
    public int numDecodings(String s) {
        if(s==null||s.length()==0)
            return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)=='0'?0:1;
        for (int i = 2; i <=n; i++) {
            if(s.charAt(i-1)!='0')
            {
                dp[i]+=dp[i-1];
            }
            if(s.charAt(i-2)=='0')
                continue;
            if(Integer.valueOf(s.substring(i-2,i))<=26)
                dp[i]+=dp[i-2];
        }
        return dp[n];
    }
//47. 礼物的最大价值
//    链接：https://www.nowcoder.com/questionTerminal/72a99e28381a407991f2c96d8cb238ab
//    来源：牛客网
//
//    小东所在公司要发年终奖，而小东恰好获得了最高福利，他要在公司年会上参与一个抽奖游戏，游戏在一个6*6的棋盘上进行，上面放着36个价值不等的礼物，每个小的棋盘上面放置着一个礼物，他需要从左上角开始游戏，每次只能向下或者向右移动一步，到达右下角停止，一路上的格子里的礼物小东都能拿到，请设计一个算法使小东拿到价值最高的礼物。
//
//    给定一个6*6的矩阵board，其中每个元素为对应格子的礼物价值,左上角为[0,0],请返回能获得的最大价值，保证每个礼物价值大于100小于1000。
    public int getMost(int[][] board) {
        // write code here
        int h = board.length;
        int w = board[0].length;
        int[][] value= new int[h+1][w+1];
        for (int i = 0; i <=h ; i++) {
            value[0][i] = 0;
        }
        for (int i = 0; i <=h ; i++) {
            value[i][0] = 0;
        }
        for (int i = 1; i <=h ; i++) {
            for (int j = 1; j <=w ; j++) {
                value[i][j] = Math.max(value[i-1][j],value[i][j-1])+board[i-1][j-1];
            }
        }
        return value[h][w];
    }
//    48. 最长不含重复字符的子字符串
//    输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。
//    例如对于 arabcacfr，最长不含重复字符的子字符串为 acfr，长度为 4。
    public static int longestSubStringWithoutDuplication(String str) {
        if(str==null||str.length()==0)
            return 0;
        char[] ch = str.toCharArray();
        int max = 0;
        int[] array = new int[str.length()];
        array[0]=1;
        for (int i = 1; i <str.length() ; i++) {
            int ii = array[i-1];
            int c = i-1;
            boolean flag = false;
            while(ii!=0)
            {
                if(ch[c--]==ch[i])
                {
                    flag=true;
                    break;
                }
                ii--;
            }
            if(flag)
                array[i]=1;
            else
                array[i]=array[i-1]+1;
        }
        for (int i = 0; i <array.length ; i++) {
            if(array[i]>max) {
                max = array[i];
                System.out.println(i);
            }
        }
        return max;
    }

//49. 丑数
//    把只包含质因子2、3和5的数称作丑数（Ugly Number）。
//    例如6、8都是丑数，但14不是，因为它包含质因子7。
//    习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    public int GetUglyNumber_Solution(int index) {
        if(index==0)
            return 0;
        if(index==1)
            return 1;
        int[] res = new int[index];
        res[0] = 1;
        int t2=0,t3=0,t5=0;
        for(int i=1;i<index;i++)
        {
            res[i] = Math.min(Math.min(res[t2]*2,res[t3]*3),res[t5]*5);
            if(res[i]==res[t2]*2)t2++;
            if(res[i]==res[t3]*3)t3++;
            if(res[i]==res[t5]*5)t5++;
        }
        return res[index-1];
    }


}

