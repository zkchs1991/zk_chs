package com.github.concurrentPrac

import scala.concurrent._
/**
  * Created by zk_chs on 16/7/17.
  */
package object ch3 {

  def execute(body: =>Unit) = ExecutionContext.global.execute(
    new Runnable {
      override def run(): Unit = body
    }
  )

}
