package com.haoran.sort;

/**
 * 斐波拉契数列（递归实现）
 */
public class FibonacciSequence {

    public static void main(String args[]){
        int fn = fibonacciSequence(25);
        System.out.println(fn);
    }

    public static int fibonacciSequence(int i){
        if (i == 1 || i == 2){
            return 1;
        }
        else{
            return fibonacciSequence(i - 1) + fibonacciSequence(i - 2);
        }
    }
}
