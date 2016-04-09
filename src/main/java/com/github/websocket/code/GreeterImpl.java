package com.github.websocket.code;

import com.github.web.grpc.GreeterGrpc;
import com.github.web.grpc.Helloworld;
import io.grpc.stub.StreamObserver;

/**
 * Created by zk_chs on 16/4/9.
 */
public class GreeterImpl implements GreeterGrpc.Greeter{

    @Override
    public void sayHello(Helloworld.HelloRequest req, StreamObserver<Helloworld.HelloReply> responseObserver) {
        Helloworld.HelloReply reply = Helloworld.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
