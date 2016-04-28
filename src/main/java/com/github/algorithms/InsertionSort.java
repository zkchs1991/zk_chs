package com.github.algorithms;

import java.util.Arrays;

/**
 * Created by zk_chs on 16/4/28.
 */
public class InsertionSort extends Example {

    @Override
    void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++)
            for (int j = i; j > 0; j--)
                if (less(arr[j], arr[j - 1]))
                    exch(arr, j, j - 1);
    }

    public static void main(String[] args) {
        Integer[] arr = RandomArr.shuffle(30);
        System.out.println(Arrays.toString(arr));
        InsertionSort sort = new InsertionSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

}
