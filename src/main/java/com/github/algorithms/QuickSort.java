package com.github.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by zk_chs on 16/4/28.
 */
public class QuickSort extends Example {

    @Override
    void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    void sort(Comparable[] arr, int lo, int hi) {
        if (lo < hi){
            int i = lo, j = hi;
            Comparable x = arr[i];
            /** 以下的判断一个都不能少 */
            while (i < j){
                /** 如果却少i < j,可能会发生less一直为true的情况,发生异常 */
                while (i < j && less(x, arr[j])) // 从右向左找小于或等于x的数来填arr[i]
                    j--;
                if (i < j) // 因为上面的while循环可能会发生j--直到i < j的情况
                    arr[i++] = arr[j];
                /** 如果却少i < j,可能会发生less一直为true的情况,发生异常 */
                while (i < j && less(arr[i], x)) // 从左向右找大于或等于x的数来填arr[j]
                    i++;
                if (i < j) // 因为上面的while循环可能会发生i++直到i < j的情况
                    arr[j--] = arr[i];
            }
            arr[i] = x; // 此时i等于j
            /** 分治 */
            sort(arr, lo, i - 1);
            sort(arr, i + 1, hi);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Integer[] arr = RandomArr.shuffle(30000000);
//        write(arr, 1);
//        System.out.println(Arrays.toString(arr));
        QuickSort sort = new QuickSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
//        write(arr, 2);
//        System.out.println(Arrays.toString(arr));
        System.out.println("total: " + (end - start));
    }

    private static void write (Integer[] arr, int index) throws FileNotFoundException {
        if (index == 1){
            File file = new File("/Users/zk_chs/something/sort/quick1.txt");
            PrintWriter writer = new PrintWriter(file);
            for (Integer integer : arr){
                writer.println(integer);
            }
            writer.close();
        } else {
            File file = new File("/Users/zk_chs/something/sort/quick2.txt");
            PrintWriter writer = new PrintWriter(file);
            for (Integer integer : arr){
                writer.println(integer);
            }
            writer.close();
        }
    }

}
