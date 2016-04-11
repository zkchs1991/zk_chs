package com.github.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zk_chs on 16/4/11.
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisClusterConfigProperties {

    /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */
    List<String> nodes;

}
