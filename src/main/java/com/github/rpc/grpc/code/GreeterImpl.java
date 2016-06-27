package com.github.rpc.grpc.code;

import com.github.rpc.grpc.GreeterGrpc;
import com.github.rpc.grpc.Helloworld;
import io.grpc.stub.StreamObserver;

/**
 * Created by zk_chs on 16/4/9.
 */
public class GreeterImpl implements GreeterGrpc.Greeter {

    @Override
    public void sayHello(Helloworld.HelloRequest req, StreamObserver<Helloworld.HelloReply> responseObserver) {
        Helloworld.HelloReply reply = Helloworld.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        System.out.println("HelloWorld Server has receive a message");
        try {
            Thread.sleep(1000L * 60L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        responseObserver.onCompleted();
    }

}
