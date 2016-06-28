package com.github.jdk.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by zk_chs on 16/6/23.
 */
public class Lambda {

    public static void main(String[] args) {
        /** generate a list */
        /** 其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环 */
        List<Integer> list = Stream.iterate(1, item -> item + 1).limit(10).collect(toList());
        System.out.println(list);

        /** make all subList's elements to one list */
        List<List<Integer>> outer = new ArrayList<>();
        List<Integer> inner1 = new ArrayList<>();
        inner1.add(1);
        List<Integer> inner2 = new ArrayList<>();
        inner1.add(2);
        List<Integer> inner3 = new ArrayList<>();
        inner1.add(3);
        List<Integer> inner4 = new ArrayList<>();
        inner1.add(4);
        List<Integer> inner5 = new ArrayList<>();
        inner1.add(5);
        outer.add(inner1);
        outer.add(inner2);
        outer.add(inner3);
        outer.add(inner4);
        outer.add(inner5);
        List<Integer> result = outer.stream().flatMap(inner -> inner.stream().map(i -> i + 1)).collect(toList());
        System.out.println(result);

        /** peek 对每个元素执行操作并返回一个新的 Stream */
        result.stream().peek((i) -> System.out.println(i)).collect(toList());
    }


}
