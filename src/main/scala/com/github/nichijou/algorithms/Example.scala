package com.github.nichijou.algorithms

/**
  * Created by zk_chs on 16/4/26.
  */
abstract class Example[T <% Ordered[T]] extends App {

  def sort(a: Array[T])

  def less(v: T, w: T) = {
    v < w
  }

  def exch(a: Array[T], v: Int, w: Int) = {
    val t = a(v)
    a(v) = a(w)
    a(w) = t
  }

  def show(a: Array[T]) = {
    println(a.mkString(" "))
  }

//  scala> import scala.math.Ordering.Implicits._
//  import scala.math.Ordering.Implicits._
//
//  scala> def cmpSome[T: Ordering](x: Option[T], y: Option[T]) = x < y
//  cmpSome: [T](x: Option[T], y: Option[T])(implicit evidence$1: Ordering[T])Boolean

}
