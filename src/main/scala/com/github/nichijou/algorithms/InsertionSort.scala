package com.github.nichijou.algorithms

import com.github.nichijou.utils.RandomArr

/**
  * Created by zk_chs on 16/4/26.
  * 插入排序,当倒置的数量很少时,插入排序很可能就是最快的排序算法
  */
object InsertionSort extends Example[Int]{

  override def sort(a: Array[Int]) = {
    val length = a.length
    for (i <- 1 until length)
      for (j <- i to 1 by -1)
        if (less(a(j), a(j - 1)))
          exch(a, j, j - 1)
    show(a)
  }

  sort(RandomArr.shuffle(10))

}
