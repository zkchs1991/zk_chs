package com.github.concurrentPrac
package ch3

import java.util.concurrent.atomic.{AtomicBoolean, AtomicLong}

object AtomicUid extends App{

  private val uid = new AtomicLong(0L)
  def getUniqueId(): Long = uid.incrementAndGet()
  execute{ log(s"Uid asynchronously: ${getUniqueId()}") }
  log(s"Got a unique id: ${getUniqueId()}")

}

object AtomicLock extends App{

  private val lock = new AtomicBoolean(false)
  def mySynchronized(body: =>Unit): Unit = {
    while(!lock.compareAndSet(false, true)) {}
    try body finally lock.set(false)
  }

  var count = 0
  for (i<- 0 until 10) execute { mySynchronized{ count += 1; log(s"$count") } }
  Thread.sleep(1000)
  log(s"Count is: $count")

}