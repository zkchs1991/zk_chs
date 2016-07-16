package com.github.concurrentPrac
package exercise.ch2

/**
  * Created by zk_chs on 16/7/16.
  */
object Ex04 extends App{

  class SyncVar[T] {

    private var empty: Boolean = true
    private var x: T = null.asInstanceOf[T]

    def get(): T = {
      if (empty) throw new Exception("must be none-empty")
      else {
        empty = true
        val v = x
        x = null.asInstanceOf[T]
        v
      }
    }

    def put(x: T): Unit = {
      if (!empty) throw new Exception("must be empty")
      else {
        empty = false
        this.x = x
      }
    }

    def isEmpty = synchronized {
      empty
    }

    def nonEmpty = synchronized {
      !empty
    }

  }

  import com.github.concurrentPrac.ch2.thread

  val syncVar = new SyncVar[Int]

  val producer = thread {
    var x = 0
    while (x < 15) {
      if (syncVar.isEmpty){
        syncVar.put(x)
        x += 1
      }
    }
  }

  val consumer = thread {
    var x = 0
    while (x != 15) {
      if (syncVar.nonEmpty){
        log(s"get = ${syncVar.get}")
        x += 1
      }
    }
  }

  producer.join()
  consumer.join()

}
