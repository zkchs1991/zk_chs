package com.github.jdk.proxy;

/**
 * Created by zk_chs on 16/6/28.
 */
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public String say(String name) {
        return "hello " + name;
    }
}
