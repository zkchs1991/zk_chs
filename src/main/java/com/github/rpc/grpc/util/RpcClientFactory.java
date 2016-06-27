package com.github.rpc.grpc.util;

import com.github.rpc.grpc.code.HelloWorldClient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by zk_chs on 16/6/23.
 */
public class RpcClientFactory extends BasePooledObjectFactory<HelloWorldClient> {


    @Override
    public HelloWorldClient create() throws Exception {
        return new HelloWorldClient("localhost", 50051);
    }

    @Override
    public PooledObject<HelloWorldClient> wrap(HelloWorldClient client) {
        return new DefaultPooledObject<>(client);
    }

    public static void main(String[] args) throws Exception {

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        /** 下面的配置均为默认配置,默认配置的参数可以在BaseObjectPoolConfig中找到 */
        poolConfig.setMaxTotal(8); // 池中的最大连接数
        poolConfig.setMinIdle(0); // 最少的空闲连接数
        poolConfig.setMaxIdle(8); // 最多的空闲连接数
        poolConfig.setMaxWaitMillis(-1); // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
        poolConfig.setLifo(true); // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
        poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L); // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞,默认为true

        GenericObjectPool<HelloWorldClient> objectPool = new GenericObjectPool<>(new RpcClientFactory(), poolConfig);

        new Thread(make(objectPool)).start();
        new Thread(make(objectPool)).start();
        new Thread(make(objectPool)).start();
        new Thread(make(objectPool)).start();

    }

    private static Runnable make (GenericObjectPool<HelloWorldClient> objectPool){
        return () -> {
            HelloWorldClient client = null;
            try {
                client = objectPool.borrowObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String user = "world";
                client.greet(user);
            } finally {
                try {
                    client.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}