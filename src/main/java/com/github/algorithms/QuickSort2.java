package com.github.algorithms;

import java.util.Arrays;

/**
 * Created by zk_chs on 16/4/28.
 */
public class QuickSort2 extends Example {

    @Override
    void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort (Comparable[] arr, int lo, int hi){
        if (lo >= hi) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    public int partition (Comparable[] arr, int lo, int hi){
        int i = lo, j = hi + 1;
        Comparable x = arr[i];
        while (true){
            while (less(arr[++i], x)) if (i == hi) break;
            while (less(x, arr[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = RandomArr.shuffle(30000);
        System.out.println(Arrays.toString(arr));
        QuickSort2 sort = new QuickSort2();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

}
