package com.github.resources

import java.util

import com.github.utils.M
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.RedisClusterConnection
import org.springframework.data.redis.connection.SortParameters.Order
import org.springframework.data.redis.core.{DefaultTypedTuple, StringRedisTemplate}
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.data.redis.core.query.SortQueryBuilder
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}
import redis.clients.jedis._

import scala.collection.JavaConverters._
import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/13.
  * StringRedisTemplate只能操作String对象
  * 如果需要对对象进行操作的话,需要自行扩展RedisTemplate<K, V>
  */
@RestController
@RequestMapping(value = Array("/redis"))
class RedisResource @Autowired() (private var stringRedisTemplate: StringRedisTemplate,
                                 private var connection: RedisClusterConnection) {

  @RequestMapping(value = Array("/add"), method = Array(RequestMethod GET, RequestMethod POST))
  def add(@RequestParam(value = "key", required = false) key: String,
         @RequestParam(value = "value", required = false) value: String) = {
    stringRedisTemplate opsForValue() set (key, value)
    M builder() put("status", "success") put ("key", key) put("value", stringRedisTemplate opsForValue() get key) buildMap
  }

  /** redis排序功能 */
  def sort = {
    stringRedisTemplate sort (SortQueryBuilder sort "sort-key" by "post:*->time" alphabetical false order Order.DESC  get "post:*->time" build(), "store-key")
  }

  /** 使用redis实现任务队列,入队 */
  def pushToTaskQueue(task: String) = {
    stringRedisTemplate opsForList() leftPush("task-queue", task)
  }

  /** 使用redis实现任务队列,出队 */
  def popFromTaskQueue = {
    val task = stringRedisTemplate opsForList() rightPop "task-queue"
    task
  }

  /** 使用redis实现优先级队列,出队 */
  def popFromPriorityQueue = {
    // 初始化3个key,即我们的list,也可以通过配置文件进行配置,然后@Autowired导入
    val queue1 = "queue:1" getBytes
    val queue2 = "queue:2" getBytes
    val queue3 = "queue:3" getBytes
    // 如果多个键都有元素,则按照顺序从左到右的顺序取第一个键中的一个元素,返回值有两项,1为队列名称,2为取出的元素
    val taskBytes = connection bRPop(0, queue1, queue2, queue3) get 1
    val task = new String(taskBytes)
    task
  }

  /** redis管道 */
  def pipeline = {
    connection openPipeline()
    connection incr ("key" getBytes())
    connection incr ("key" getBytes())
    val result = connection closePipeline()
    result
  }

  /** zset测试,取出指定score范围内的member */
  def betweenUseInZset = {
    val set = new util.HashSet[TypedTuple[String]]()
    val tuple1 = new DefaultTypedTuple[String]("Tom", 89.0)
    val tuple2 = new DefaultTypedTuple[String]("Peter", 67.0)
    val tuple3 = new DefaultTypedTuple[String]("David", 100.0)
    set add tuple1; set add tuple2; set add tuple3
    stringRedisTemplate opsForZSet() add("scoreboard", set)
    val result = stringRedisTemplate opsForZSet() rangeByScore("scoreboard", 89.0, Double.MaxValue, 0, 3)
    result.asScala.foreach(println)
    result
  }

  /** 另外,redis还有pub/sub功能,但个人感觉不如kafka或其他的MQ */

}

// 不行的话,就用jedis,把pool加入到bean,然后每次用过bean来获取jedis连接
object test extends App {
  val poolConfig = new JedisPoolConfig
  poolConfig.setMaxIdle(8)
  poolConfig.setMaxTotal(8)
  val pool = new JedisPool(poolConfig, "127.0.0.1", 6379)
//  val jedis = new Jedis("127.0.0.1", 6379) 用了pool之后就不再需要每次连接了
  val jedis = pool getResource()
  val set = jedis zrangeByScore("scoreboard", "89", "+inf", 0, 1)
  set.asScala.foreach(println)
//  jedis.pipelined().exec() pipeline在jedis中的操作
  /** 以jedisCluster进行操作,HostAndPort可以传入多个,防止单个节点挂掉 */
  val cluster = new JedisCluster(new HostAndPort("127.0.0.1", 7000), poolConfig)
  val set2 = cluster zrangeByScore("scoreboard", "89", "+inf", 0, 2)
  set2.asScala.foreach(println)
}
