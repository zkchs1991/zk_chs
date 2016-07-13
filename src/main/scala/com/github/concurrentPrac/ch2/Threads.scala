package com.github.concurrentPrac
package ch2

/**
  * Created by zk_chs on 16/7/13.
  */
object ThreadsMain extends App{

  val t: Thread = Thread.currentThread
  val name = t.getName
  println(s"I am the thread $name")

}

object ThreadsCreation extends App{

  class MyThread extends Thread {
    override def run(): Unit = {
      println("New Thread running.")
    }
  }

  val t = new MyThread
  t.start()
  t.join()
  println("New thread joined.")

}

object ThreadsSleep extends App{

  val t = thread {
    Thread.sleep(1000)
    log("New thread running.")
    Thread.sleep(1000)
    log("Still running.")
    Thread.sleep(1000)
    log("Complete.")
  }
  t.join()
  log("New thread joined.")

}

object ThreadsNondeterminism extends App{

  val t = thread { log("New thread running.") }
  log("...")
  log("...")
  t.join()
  log("New thread joined.")

}

object ThreadsCommunicate extends App{

  var result: String = null
  var t = thread { result = "\nTitle\n" + "=" * 5 }
  t.join()
  log(result)

}

object ThreadsUnprotectedUid extends App{

  var uidCount = 0L
  def getUniqueId() = this.synchronized {
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }

  def printUniqueIds(n: Int): Unit = {
    val uids = for (i <- 0 until n) yield getUniqueId()
    log(s"Generated uids: $uids")
  }
  val t = thread { printUniqueIds(5) }
  printUniqueIds(5)
  t.join()

}

