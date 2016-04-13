package com.github.resources

import java.util
import com.github.utils.M
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/13.
  * StringRedisTemplate只能操作String对象
  * 如果需要对对象进行操作的话,需要自行扩展RedisTemplate<K, V>
  */
@RestController
@RequestMapping(value = Array("/redis"))
class RedisResource @Autowired() (private var stringRedisTemplate: StringRedisTemplate) {

  @RequestMapping(value = Array("/add"), method = Array(RequestMethod GET, RequestMethod POST))
  def add(@RequestParam(value = "key", required = false) key: String,
         @RequestParam(value = "value", required = false) value: String): util.Map[String, AnyRef] = {
    stringRedisTemplate opsForValue() set (key, value)
    M builder() put("status", "success") put ("key", key) put("value", stringRedisTemplate opsForValue() get key) buildMap
  }

}
