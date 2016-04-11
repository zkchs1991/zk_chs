package com.github.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by zk_chs on 16/4/11.
 */
@Configuration
public class RedisClusterConfig {

    /**
     * Type safe representation of application.properties
     */
    @Autowired
    RedisClusterConfigProperties clusterProperties;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(
                new RedisClusterConfiguration(clusterProperties.getNodes()));
    }

    @Bean
    @Autowired
    public RedisClusterConnection redisClusterConnection (RedisConnectionFactory connectionFactory){
        return connectionFactory.getClusterConnection();
    }

}
