package com.github.rpc.grpc.code;

import com.zk_chs.grpc.HelloWorldRequest;
import com.zk_chs.grpc.HelloWorldRpcServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by zk_chs on 16/6/30.
 */
public class GrpcClient {

    private final ManagedChannel channel;
    private final HelloWorldRpcServiceGrpc.HelloWorldRpcServiceBlockingStub blockingStub;

    public GrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = HelloWorldRpcServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String request(String req) {
        HelloWorldRequest request = HelloWorldRequest.newBuilder()
                .setRequest(req)
                .build();
        return blockingStub.sayHello(request).getResponse();
    }

    public static void main(String[] args) throws Exception {
        GrpcClient client = new GrpcClient("localhost", 38628);
        String req = "world!";
        String response = client.request(req);
        System.out.println(response);
    }

}
