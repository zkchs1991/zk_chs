package com.github.concurrentPrac.exercise.ch2

/**
  * Created by zk_chs on 16/7/16.
  */
object Ex03 extends App{

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

  }

}
