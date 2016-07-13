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

object ThreadsStart extends App {
  class MyThread extends Thread {
    override def run(): Unit = {
      println(s"I am ${Thread.currentThread.getName}")
    }
  }

  val t = new MyThread()
  t.start()
  println(s"I am ${Thread.currentThread.getName}")
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

object ThreadSharedStateAccessRecording extends App{

  for (i <- 0 until 100000) {
    var a = false
    var b = false
    var x = -1
    var y = -1
    val t1 = thread {
      a = true
      y = if (b) 0 else 1
    }
    val t2 = thread {
      b = true
      x = if (a) 0 else 1
    }
    t1.join()
    t2.join()
    assert(!(x == 1 && y == 1), s"x = $x, y = $y")
  }

}
