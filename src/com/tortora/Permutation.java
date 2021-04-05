package com.tortora;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        for (String s: args) {
            randomizedQueue.enqueue(s);
        }

        for (int i = 0; i < k; i++){
            StdOut.println(randomizedQueue.dequeue());
        }








    }
}
