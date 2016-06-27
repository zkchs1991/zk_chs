package com.github.rpc.grpc.customRPC;

/**
 * Created by zk_chs on 16/6/27.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
