package com.github

/**
  * Created by zk_chs on 16/7/13.
  */
package object concurrentPrac {

  def log(msg: String): Unit =
    println(s"${Thread.currentThread().getName}: $msg")

}
