package com.github.algorithms;

/**
 * Created by zk_chs on 16/4/28.
 */
public abstract class Example {

    abstract void sort(Comparable[] arr);

    public boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    public void exch (Comparable[] arr, int v, int w){
        Comparable t = arr[v];
        arr[v] = arr[w];
        arr[w] = t;
    }

}
