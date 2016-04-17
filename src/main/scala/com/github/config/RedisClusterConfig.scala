package com.github.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.{RedisClusterConnection, RedisClusterConfiguration, RedisConnectionFactory}
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import redis.clients.jedis.JedisPoolConfig

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/13.
  */
@Configuration
class RedisClusterConfig extends {

  @Autowired
  private var clusterProperties: RedisClusterConfigProperties = _

  @Bean
  def connectionFactory: RedisConnectionFactory = {
    val factory = new JedisConnectionFactory(new RedisClusterConfiguration(clusterProperties getNodes))
    /** 如果需要定制连接池,可以使用下面的方式进行配置 */
//    val pool = new JedisPoolConfig
//    pool setMaxIdle 8
//    pool setMaxTotal 8
//    pool set
//    factory.setPoolConfig(pool)
    factory
  }

  /**
    * 对redis的存取,据需要先行转换成byte[]
    **/
  @Bean
  @Autowired
  def redisClusterConnection(connectionFactory: RedisConnectionFactory): RedisClusterConnection = {
    connectionFactory getClusterConnection
  }

  /**
    * 该bean可以直接操作String,无需转成byte[]
    **/
  @Bean
  @Autowired
  def stringRedisTemplate(connectionFactory: RedisConnectionFactory): StringRedisTemplate = {
    new StringRedisTemplate(connectionFactory)
  }

}
