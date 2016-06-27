package com.github.rpc.grpc.customRPC;

/**
 * Created by zk_chs on 16/6/27.
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }

}
