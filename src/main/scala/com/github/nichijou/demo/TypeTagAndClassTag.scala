package com.github.nichijou.demo

import scala.reflect._
import scala.reflect.runtime.universe._

/**
  * Created by zk_chs on 16/4/23.
  */
object TypeTagAndClassTag extends App {

  def print1[T](x: List[T])(implicit m: Manifest[T]) = {
    if (m <:< manifest[String])
      println("字符串类型的List")
    else
      println("非字符串类型的List")
  }
  print1(List("one", "two"))
  print1(List(1, 2))
  print1(List("one", 2))

  def getTypeTag[T: TypeTag](a: T) = typeTag[T]
  //下列语句返回TypeTag[List[Int]]
  println(getTypeTag(List(1, 2, 3)))

  def patternMatch[A : TypeTag](xs: List[A]) = typeOf[A] match {
    //利用类型约束进行精确匹配
    case t if t =:= typeOf[String] => "list of strings"
    case t if t <:< typeOf[Int] => "list of ints"
  }
  println(patternMatch(List(1,2)))

  val tt = typeTag[Int]
  println(tt) // reflect.runtime.universe.TypeTag[Int] = TypeTag[Int]
  val ct = classTag[String]
  println(ct) // 得到具体类型 scala.reflect.ClassTag[String] = java.lang.String

}
