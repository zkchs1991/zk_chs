package com.github.apache.activemq;

import org.springframework.jms.annotation.JmsListener;

/**
 * Created by zk_chs on 16/4/9.
 */
//@Component
public class Consumer {

    @JmsListener(destination = "github-queue")
    public void receiveQueue(String text) {
        System.out.println(text);
    }

}
