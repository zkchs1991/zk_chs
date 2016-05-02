package com.github.algorithms;

/**
 * Created by zk_chs on 16/4/28.
 */
public class SelectionSort extends Example {

    @Override
    void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++){
            int min = i;
            for (int j = i; j < arr.length; j++)
                if (less(arr[j], arr[min])) min = j;
            exch(arr, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = RandomArr.shuffle(300000);
//        System.out.println(Arrays.toString(arr));
        SelectionSort sort = new SelectionSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
//        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

}
