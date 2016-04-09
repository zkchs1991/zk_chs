package com.github.apache.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by zk_chs on 16/4/9.
 */
@EnableJms
@Configuration
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
                        ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        return activeMQConnectionFactory;
    }

}
