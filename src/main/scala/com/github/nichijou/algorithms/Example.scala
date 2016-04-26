package com.github.nichijou.algorithms

/**
  * Created by zk_chs on 16/4/26.
  */
trait Example {

  def sort(a: Array[Int])

  def less(v: Int, w: Int) = {
    v < w
  }

  def exch(a: Array[Int], v: Int, w: Int) = {
    val t = a(v)
    a(v) = a(w)
    a(w) = t
  }

  def show(a: Array[Int]) = {
    println(a.mkString(" "))
  }

}
