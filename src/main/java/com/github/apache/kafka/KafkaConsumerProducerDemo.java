package com.github.apache.kafka;

/**
 * Created by zk_chs on 16/4/9.
 */
public class KafkaConsumerProducerDemo {

    public static void main(String[] args) {
        new Producer().start();
        new Consumer().start();
    }

}
