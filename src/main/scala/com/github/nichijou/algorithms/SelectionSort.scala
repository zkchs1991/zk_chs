package com.github.nichijou.algorithms

import com.github.nichijou.utils.RandomArr

/**
  * Created by zk_chs on 16/4/26.
  * 选择排序,运行时间和输入无关
  */
object SelectionSort extends Example{

  override def sort[T <% Ordered[T]](a: Array[T]) = {
    val length = a.length
    for (i <- a.indices){ // 与 0 until length 相同
      var min = i
      for (j <- (i + 1) until length)
        if (less(a(j), a(min))) min = j
      exch(a, i, min)
    }
    show(a)
  }

//  println("SelectionSort".toArray.mkString(" "))
//  sort("SelectionSort".toArray)

  val s = "asmnqoakqwnkdksfpqpowqenvsjhsakewqncammxnckjehuiuqwnejkasjncsjakbdqwijncajxkajdkqjwhejnjkxcnajkfbocviuwetiyu"
  val length = s.length
  println(s.toArray.mkString(" "))
  val start = System.currentTimeMillis()
  sort(s.toArray)
  val end = System.currentTimeMillis()
  println("total: " + (end - start))

//  sort(RandomArr.shuffle(10))

}
