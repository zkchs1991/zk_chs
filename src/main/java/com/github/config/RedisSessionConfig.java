package com.github.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by zk_chs on 16/4/11.
 * 这个配置是用来开启session共享的,使用redis存储session
 * redis连接配置位置在application.properties中
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)/** 默认即为1800s */
public class RedisSessionConfig {
}