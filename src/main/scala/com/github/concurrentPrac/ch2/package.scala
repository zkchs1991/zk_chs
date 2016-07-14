package com.github.concurrentPrac

/**
  * Created by zk_chs on 16/7/13.
  */
package object ch2 {

  def thread(body: => Unit): Thread = {
    val t = new Thread{
      override def run() = body
    }
    t.start()
    t
  }

}
