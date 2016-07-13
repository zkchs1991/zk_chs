package com.github.concurrentPrac.exercise.ch1

/**
  * Created by zk_chs on 16/7/14.
  */
object Ex3 extends App{

  def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = xs.forall { x =>
    try {
      pred(x)
    } catch {
      case _: Exception => false
    }
  }

}
