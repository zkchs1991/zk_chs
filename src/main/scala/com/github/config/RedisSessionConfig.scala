package com.github.config

import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

/**
  * Created by zk_chs on 16/4/13.
  */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)/** 默认即为1800s */
class RedisSessionConfig {

}
