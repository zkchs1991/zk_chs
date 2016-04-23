package com.github.nichijou

import scala.reflect.runtime.universe._

/**
  * Created by zk_chs on 16/4/22.
  * scala中type与class区别
  */
object TypeOfAndClassOf extends App {

  /** class(类)与type(类型)的区别,类型(type)比类(class)更具体 */
  val classDiff = classOf[List[Int]] == classOf[List[String]] // true
  val typeDiff = typeOf[List[Int]] == typeOf[List[String]] // false
  println("classDiff => " + classDiff + "  typeDiff => " + typeDiff)

}
