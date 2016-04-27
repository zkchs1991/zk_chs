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

}
