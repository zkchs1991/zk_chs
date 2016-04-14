package com.github.apache.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

/**
 * Created by zk_chs on 16/4/9.
 */
//@EnableJms
//@Configuration
public class ActiveMQ4Config {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("github-queue");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory (){
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(
                        ActiveMQConnectionFactory.DEFAULT_USER,
                        ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                        "failover:(tcp://127.0.0.1:61616,tcp://127.0.0.1:61617)");
//                        ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        return activeMQConnectionFactory;
    }

}
