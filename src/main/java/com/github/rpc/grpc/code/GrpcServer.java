package com.github.rpc.grpc.code;

import com.zk_chs.grpc.HelloWorldRpcServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Created by zk_chs on 16/6/30.
 */
public class GrpcServer {

    private final int port = 38628;

    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(HelloWorldRpcServiceGrpc.bindService(new HelloWorldRpcServiceImpl()))
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                GrpcServer.this.stop();
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final GrpcServer server = new GrpcServer();
        server.start();
        server.blockUntilShutdown();
    }

}
