package com.github.nichijou.algorithms

import com.github.nichijou.utils.RandomArr

/**
  * Created by zk_chs on 16/4/26.
  * 希尔排序,基于插入排序,交换不相邻的元素对数组局部进行排序
  */
object ShellSort extends Example[Int] {

  override def sort(a: Array[Int]) = {
    val length = a.length
    var h = 1
    while ( h < length/3 ) h = 3*h + 1 // 1, 4, 13, 40, 121, 364, 1093
    while ( h >= 1 ){
      for ( i <- h until length )
        for ( j <- i to h by -h )
          if (less(a(j), a(j - h)))
            exch(a, j, j - h)
      h = h / 3
    }
    show(a)
  }

  sort(RandomArr.shuffle(10))

}
