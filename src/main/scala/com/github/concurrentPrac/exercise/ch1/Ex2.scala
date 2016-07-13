package com.github.concurrentPrac.exercise.ch1

/**
  * Created by zk_chs on 16/7/13.
  */
object Ex2 extends App{

  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = for {
    aVal <- a
    bVal <- b
  } yield (aVal, bVal)

}
