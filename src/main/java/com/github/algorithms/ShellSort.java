package com.github.algorithms;

import java.util.Arrays;

/**
 * Created by zk_chs on 16/4/28.
 */
public class ShellSort extends Example {

    @Override
    void sort(Comparable[] arr) {
        int h = 1;
        while (h < arr.length / 3) h = 3*h + 1;
        while (h > 0){
            for (int i = h; i < arr.length; i++)
                for (int j = i; j >= h; j -= h)
                    if (less(arr[j], arr[j - h]))
                        exch(arr, j, j - h);
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = RandomArr.shuffle(30);
        System.out.println(Arrays.toString(arr));
        ShellSort sort = new ShellSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

}
