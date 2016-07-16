package com.github.concurrentPrac
package exercise.ch2

/**
  * Created by zk_chs on 16/7/16.
  */
object Ex02 extends App{

  def periodically(duration: Long)(b: () =>Unit): Unit = {

    val worker = new Thread {
      while(true) {
        b()
        Thread.sleep(duration)
      }
    }

    worker.setName("Worker");
    worker.setDaemon(true)
    worker.start()

  }

}
