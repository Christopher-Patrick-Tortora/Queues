package com.tortora;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int n = 0;
    private static final int INIT_CAPACITY = 8;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == queue.length) {
            resize(2 * queue.length);
        }
        queue[n] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        int index = StdRandom.uniform(n);
        Item item = queue[index];
        queue[index] = null;
        n--;
        for (int i = 0; i < n; i++) {
            if (queue[i] == null && queue[i + 1] != null) {
                queue[i] = queue[i + 1];
                queue[i + 1] = null;
            }
        }
        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index = StdRandom.uniform(n);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        class ListIterator implements Iterator<Item> {
            private final Item[] shuffledQueue;
            private int index;

            public ListIterator() {
                shuffledQueue = (Item[]) new Object[n];
                for (int i = 0; i < n; i++) {
                    shuffledQueue[i] = queue[i];
                }
                StdRandom.shuffle(shuffledQueue);
                index = 0;
            }

            public boolean hasNext() {
                return n > 1 && index < n;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Item next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return shuffledQueue[index++];
            }
        }
        return new ListIterator();
    }

    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
            StdOut.println(randomizedQueue.isEmpty());
            StdOut.println(randomizedQueue.size());
            randomizedQueue.enqueue("1");
            randomizedQueue.enqueue("2");
            randomizedQueue.enqueue("3");
            randomizedQueue.enqueue("4");
            StdOut.println(randomizedQueue.dequeue());
            StdOut.println(randomizedQueue.sample());
            for (String s : randomizedQueue){
                StdOut.println(s);
            }

        }


    }

