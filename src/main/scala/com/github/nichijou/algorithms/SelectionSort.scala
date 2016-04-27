package com.github.nichijou.algorithms

import com.github.nichijou.utils.RandomArr

/**
  * Created by zk_chs on 16/4/26.
  * 选择排序,运行时间和输入无关
  */
object SelectionSort extends Example[Char]{

  override def sort(a: Array[Char]) = {
    val length = a.length
    for (i <- a.indices){ // 与 0 until length 相同
      var min = i
      for (j <- (i + 1) until length)
        if (less(a(j), a(min))) min = j
      exch(a, i, min)
    }
    show(a)
  }

  println("SelectionSort".toArray.mkString(" "))
  sort("SelectionSort".toArray)

//  sort(RandomArr.shuffle(10))

}
