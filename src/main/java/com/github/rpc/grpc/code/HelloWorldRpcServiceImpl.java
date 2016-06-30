package com.github.rpc.grpc.code;

import com.zk_chs.grpc.HelloWorldRequest;
import com.zk_chs.grpc.HelloWorldResponse;
import com.zk_chs.grpc.HelloWorldRpcServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Created by zk_chs on 16/6/30.
 */
public class HelloWorldRpcServiceImpl implements HelloWorldRpcServiceGrpc.HelloWorldRpcService {

    @Override
    public void sayHello(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {
        String req = request.getRequest();
        HelloWorldResponse resp = HelloWorldResponse.newBuilder()
                .setResponse("hello " + req)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

}
