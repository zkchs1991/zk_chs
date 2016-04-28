package com.github.algorithms;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by zk_chs on 16/4/28.
 */
public class RandomArr {

    public static Integer[] shuffle (int length){
        if (length <= 0) return new Integer[0];
        Integer[] arr = new Integer[length];
        Random random = new Random();
        arr[0] = 0;
        for (int i = 1; i < length; i++){
            int randomIndex = random.nextInt(i + 1);
            arr[i] = arr[randomIndex];
            arr[randomIndex] = i;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shuffle(30)));
    }

}
