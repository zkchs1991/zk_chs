package com.github.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/14.
  */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
class RedisClusterConfigProperties {

  var nodes: java.util.List[java.lang.String] = _

  def getNodes = nodes

  def setNodes(nodes: java.util.List[java.lang.String]) {this nodes = nodes}

}
