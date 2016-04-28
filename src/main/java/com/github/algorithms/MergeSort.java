package com.github.algorithms;

import java.util.Arrays;

/**
 * Created by zk_chs on 16/4/28.
 */
public class MergeSort extends Example {

    private Comparable[] aux;

    @Override
    void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private void sort (Comparable[] arr, int lo, int hi){
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge (Comparable[] arr, int lo, int mid, int hi){
        int i = lo; int j = mid + 1;
        for (int k = lo; k <= hi; k++) aux[k] = arr[k];
        for (int k = lo; k <= hi; k++)
            if (i > mid)                    arr[k] = aux[j++];
            else if (j > hi)                arr[k] = aux[i++];
            else if (less(aux[j], aux[i]))  arr[k] = aux[j++];
            else                            arr[k] = aux[i++];
    }

    public static void main(String[] args) {
        Integer[] arr = RandomArr.shuffle(30);
        System.out.println(Arrays.toString(arr));
        MergeSort sort = new MergeSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

}
