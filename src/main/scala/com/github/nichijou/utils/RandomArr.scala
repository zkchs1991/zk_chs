package com.github.nichijou.utils

import scala.util.Random

/**
  * Created by zk_chs on 16/4/26.
  */
object RandomArr {

  def shuffle(length: Int) = {
    if (length <= 0) Array
    val random = Random
    val arr = new Array[Int](length)
    arr(0) = 0
    for (i <- 1 until length) {
      val randomIndex = random.nextInt(i + 1)
      arr(i) = arr(randomIndex)
      arr(randomIndex) = i
    }
    arr
  }

  def main(args: Array[String]) {
    val arr = shuffle(10)
    for (elem <- arr) {print(elem)}
  }

}
