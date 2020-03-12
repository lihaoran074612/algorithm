package com.haoran.tree;

/**
 * BTree遍历
 */
public class BTree {
    public static void main(String[] args) {
        //自定义tree数据结构
        Tree t = new Tree();

        t.insert(5, 5);
        t.insert(1,  1);
        t.insert(2,  2);
        t.insert(0,  0);
        t.insert(8,  8);
        t.insert(13,  13);

        t.posOrderTra(t.root);
        System.out.println();
        t.posOrderTra();
    }
}

class Tree{
    Node root;

    public Tree() {
        root = null;
    }

    public Node find(int key) {
        if(root == null) {
            return null;
        }
        Node node = root;
        while(node != null) {
            //小于节点值 在左子树查找
            if(node.key > key){
                node = node.leftChild;
            }
            //找到
            if(node.key == key){
                node.displayNode();
                return node;
            }
            //大于节点值 在左子树查找
            if(node.key < key) {
                node = node.rightChild;
            }
        }
        return null;
    }

    public void insert(int key, int data) {
        Node newNode = new Node(key, data);
        newNode.leftChild = null;
        newNode.rightChild = null;

        if(root == null) {
            root = newNode;
            return;
        }

        Node node = root;
        while(true) {
            if(key > node.key) {
                if(node.rightChild == null) {
                    node.rightChild = newNode;
                    return;
                }else {
                    node = node.rightChild;
                }
            }
            if(node.key == key) {
                return;
            }
            if(key < node.key) {
                if(node.leftChild == null) {
                    node.leftChild = newNode;
                    return;
                }else {
                    node = node.leftChild;
                }
            }
        }
    }

    public Node delete(int key) {
        if(root == null) {
            return null;
        }
        Node node = root;
        if(root.key == key) {
            root = null;
            return node;
        }
        //与查找不同 因为还要保留父节点的信息才能准确的插入
        while(node != null) {
            if(node.key > key) {
                if(node.leftChild == null) {
                    return null;
                }
                if(node.leftChild.key == key) {
                    Node n = node.leftChild;
                    if(n.leftChild == null && n.rightChild == null) {
                        node.leftChild = null;
                        return n;
                    }
                    if(n.leftChild == null) {
                        node.leftChild = n.rightChild;
                        return n;
                    }
                    if(n.rightChild == null) {
                        node.leftChild = n.leftChild;
                        return n;
                    }
                }else {
                    node = node.leftChild;
                }
            }
            if(root.key < key) {
                if(node.rightChild == null) {
                    return null;
                }
                if(node.rightChild.key == key) {
                    Node n = node.rightChild;
                    if(n.leftChild == null && n.rightChild == null) {
                        node.rightChild = null;
                        return n;
                    }
                    if(n.leftChild == null) {
                        node.rightChild = n.rightChild;
                        return n;
                    }
                    if(n.rightChild == null) {
                        node.rightChild = n.leftChild;
                        return n;
                    }
                }else {
                    node = node.rightChild;
                }
            }
        }
        return null;
    }

    //递归 中序遍历
    public void inOrderTra(Node localRoot) {
        if(localRoot != null) {
            inOrderTra(localRoot.leftChild);
            localRoot.displayNode();
            inOrderTra(localRoot.rightChild);
        }
    }

    //递归 先序遍历
    public void preOrderTra(Node localRoot) {
        if(localRoot != null) {
            localRoot.displayNode();
            preOrderTra(localRoot.leftChild);
            preOrderTra(localRoot.rightChild);
        }
    }

    //	递归 后序遍历
    public void posOrderTra(Node localRoot) {
        if(localRoot != null) {
            posOrderTra(localRoot.leftChild);
            posOrderTra(localRoot.rightChild);
            localRoot.displayNode();
        }
    }

    //层序遍历
    public void levelTra() {
        if(root == null) {
            return;
        }
        Queue q = new Queue(20);
        q.insert(root);
        while(!q.isEmpty()) {
            Node node = q.remove();
            node.displayNode();
            if(node.leftChild != null) {
                q.insert(node.leftChild);
            }
            if(node.rightChild != null) {
                q.insert(node.rightChild);
            }
        }
        System.out.println();
    }

    //非递归 中序遍历
    public void inOrderTra() {
        Stack s = new Stack(20);
        Node node = root;
        while(!s.isEmpty() || node != null) {
            if(node != null) {
                s.push(node);
                node = node.leftChild;
            }else {
                node = s.pop();
                node.displayNode();
                node = node.rightChild;
            }
        }
        System.out.println();
    }

    //非递归 先序遍历
    public void preOrderTra() {
        Stack s = new Stack(20);
        Node node = root;
        while(!s.isEmpty() || node != null) {
            if(node != null){
                node.displayNode();
                s.push(node);
                node = node.leftChild;
            }else{
                node = s.pop().rightChild;
            }
        }
        System.out.println();
    }

    //非递归 后序遍历
    public void posOrderTra() {
        if(root == null){
            return;
        }
        Stack s = new Stack(20);
        Node node = root;
        Node last = null;
        while(!s.isEmpty() || node != null) {
            if(node != null) {
                boolean shouldDisplay = (node.rightChild == null && last == node.leftChild) || last == node.rightChild;
                if(shouldDisplay){
                    node.displayNode();
                    if(s.isEmpty()){
                        return;
                    }
                    last = node;
                    node = s.pop();
                }else {
                    s.push(node);
                    if(node.rightChild != null) {
                        s.push(node.rightChild);
                    }
                    if(node.leftChild != null){
                        node = node.leftChild;
                    }else{
                        last = node.leftChild;
                        node = s.pop();
                    }
                }
            }else{
                last = node.rightChild;
            }
        }
        System.out.println();
    }

}

class Node{
    int key;
    int data;

    Node leftChild;
    Node rightChild;

    public Node(int key, int data) {
        this.key = key;
        this.data = data;
    }

    public void displayNode() {
        System.out.print(data + " ");
    }

    @Override
    public String toString() {
        return "key:" + key + "  data: " + data;
    }
}


class Queue{
    private int maxSize;
    private Node[] queArray;
    private int front;
    private int rear;
    private int num;

    public Queue(int s) {
        maxSize = s + 1;
        queArray = new Node[maxSize];
        front = 0;
        rear = -1;
    }

    public void insert(Node j) {
        if(rear == maxSize - 1){
            rear = -1 ;
        }
        queArray[++rear] = j;
        num++;
    }

    public Node remove() {
        Node temp = queArray[front++];
        if(front == maxSize) {
            front = 0;
        }
        num--;
        return temp;
    }

    public Node peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return num == 0;
    }
    public boolean isFull() {
        return num == maxSize;
    }

    public int size() {
        return num;
    }
}


class Stack{
    private int maxSize;
    private int top;
    private Node[] stackArray;

    public Stack(int size) {
        maxSize = size;
        stackArray = new Node[maxSize];
        top = -1;
    }

    public void push(Node value) {
        this.stackArray[++this.top] = value;
    }

    public Node pop() {
        return this.stackArray[this.top--];
    }

    public Node peek() {
        return this.stackArray[this.top];
    }

    public boolean isEmpty() {
        return (this.top == -1);
    }

    public boolean isFull() {
        return (this.maxSize == ++this.top);
    }
}
