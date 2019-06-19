package leetcode_top100;

import java.util.HashMap;
import java.util.LinkedList;
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//        获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
//        写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
//        进阶:
//        你是否可以在 O(1) 时间复杂度内完成这两种操作？
//        示例:
//        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1); // 返回 1
//        cache.put(3, 3); // 该操作会使得密钥 2 作废
//        cache.get(2); // 返回 -1 (未找到)
//        cache.put(4, 4); // 该操作会使得密钥 1 作废
//        cache.get(1); // 返回 -1 (未找到)
//        cache.get(3); // 返回 3
//        cache.get(4); // 返回 4

class LRUCache {
//    思路:	利用HashMap+双向链表
//    思路其实很简单: 每次put/get操作访问已经有的节点之后都要将节点移动到双向链表的尾端。
//    put入没有的结点则直接在尾部插入即可。
//    进行put操作时候，若cache空间不足，则将首结点移除(LRU最近最少用到的结点)。
    class Node{
        int key;
        int val;
        Node pre;
        Node next;
    }
    private int idx;//当前cache所存的元素值
    private int capacity;//cache总容量
    private HashMap<Integer,Node> cache;
    private Node head;// 方便O（1）移除首节点
    private Node tail;// 方便O（1）将元素移到尾端
    public LRUCache(int capacity){
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        idx = 0;
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
    }
    private void remove(Node node){//移除当前节点
        Node tempPre = node.pre;
        Node tempNext = node.next;
        tempPre.next = tempNext;
        tempNext.pre = tempPre;
    }
    private void setLast(Node node){//转移结点到尾端
        Node tempTailPre = tail.pre;
        tempTailPre.next = node;
        node.pre = tempTailPre;
        node.next = tail;
        tail.pre = node;
    }
    private void removeHead(){//移除首结点
        Node temp = head.next;
        head.next = temp.next;
        temp.next.pre = head;
        cache.remove(temp.key);
    }
    public int get(int key){
        if(cache.containsKey(key))
        {
            Node cur = cache.get(key);
            if(tail.pre==cur)
                return cur.val;
            remove(cur);
            setLast(cur);
            return cur.val;
        }
        return -1;
    }
    public void put(int key,int value){
        Node cur = cache.get(key);
        if(cur==null){
            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;
            if(idx>=capacity){//判断是否超过cache总容量
                removeHead();
            }
            ++idx;
            setLast(newNode);
            cache.put(key,newNode);
        }else {
            cur.val = value;
            remove(cur);
            setLast(cur);
        }
    }
}