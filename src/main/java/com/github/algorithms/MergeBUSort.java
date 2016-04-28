package com.github.algorithms;

import java.util.Arrays;

/**
 * Created by zk_chs on 16/4/28.
 */
public class MergeBUSort extends Example{

    private Comparable[] aux;

    @Override
    void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        for (int sz = 1; sz < arr.length; sz += sz)
            for (int lo = 0; lo < arr.length - sz; lo += 2*sz)
                merge(arr, lo, lo+sz-1, Math.min(lo+sz+sz-1, arr.length-1));
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
        MergeBUSort sort = new MergeBUSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

}
