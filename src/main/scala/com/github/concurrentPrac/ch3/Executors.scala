package com.github.concurrentPrac
package ch3

import java.util.concurrent.ForkJoinPool
import scala.concurrent._

object ExecutorsCreate extends App{

  val executor = new ForkJoinPool
  executor.execute(new Runnable {
    override def run(): Unit = log("This task is run asynchronously.")
  })
  Thread.sleep(5000)

}

object ExecutionContextGlobal extends App{

  val ectx = ExecutionContext.global
  ectx.execute(new Runnable {
    override def run(): Unit = log("Running on the execution context.")
  })
  Thread.sleep(500)

}

object ExecutionContextCreate extends App{

  val pool = new ForkJoinPool(2)
  val ectx = ExecutionContext.fromExecutorService(pool)
  ectx.execute(new Runnable {
    override def run(): Unit = log("Running on the execution context again.")
  })
  Thread.sleep(500)

}

object ExecutionContextSleep extends App{

  for (i<- 0 until 32) execute {
    Thread.sleep(2000)
    log(s"Task $i complete")
  }
  Thread.sleep(10000)

}
