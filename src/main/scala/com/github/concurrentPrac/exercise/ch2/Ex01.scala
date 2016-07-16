package com.github.concurrentPrac
package exercise.ch2

import com.github.concurrentPrac.ch2.thread
/**
  * Created by zk_chs on 16/7/16.
  */
object Ex01 extends App{

  def parallel[A, B](a: =>A, b: =>B): (A, B) = {
    var aVar = null.asInstanceOf[A]
    var bVar = null.asInstanceOf[B]

    val t1 = thread {
      aVar = a
      log(aVar.toString)
    }
    val t2 = thread {
      bVar = b
      log(bVar.toString)
    }

    t1.join(); t2.join()
    (aVar, bVar)
  }

}
