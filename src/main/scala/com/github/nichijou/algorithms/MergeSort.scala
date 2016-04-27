package com.github.nichijou.algorithms

import com.github.nichijou.utils.RandomArr

/**
  * Created by zk_chs on 16/4/27.
  * 归并排序,自顶向下,所需时间与NlogN成正比,缺点为所需的额外空间也与N成正比
  */
object MergeSort extends Example[Char] {

  var aux: Array[Char] = _

  override def sort(a: Array[Char]) = {
    aux = new Array[Char](length)
    sort(a, 0, length - 1)
    show(a)
  }

  def sort(a: Array[Char], lo: Int, hi: Int): Unit = {
    if (hi <= lo) return
    val mid = lo + (hi - lo) / 2
    if (a.length > 15) {
      sort(a, lo, mid)
      sort(a, mid + 1, hi)
      merge(a, lo, mid, hi)
    } else
      InsertionSort.sort(a) // 使用插排处理小的数组

  }

  def merge(a: Array[Char], lo: Int, mid: Int, hi: Int) = {
    var i = lo
    var j = mid + 1
    for (k <- lo to hi) aux(k) = a(k)
    for (k <- lo to hi)
      if        (i > mid)               {a(k) = aux(j); j += 1}
      else if   (j > hi)                {a(k) = aux(i); i += 1}
      else if   (less(aux(j), aux(i)))  {a(k) = aux(j); j += 1}
      else                              {a(k) = aux(i); i += 1}
  }

//  val length = 20
//  val arr = RandomArr.shuffle(length)
//  println(arr.mkChar(" "))
//  sort(arr)
  val s = "asmnqoakqwnkdksfpqpowqenvsjhsakewqncammxnckjehuiuqwnejkasjncsjakbdqwijncajxkajdkqjwhejnjkxcnajkfbocviuwetiyu"
  val length = s.length
  println(s.toArray.mkString(" "))
  val start = System.currentTimeMillis()
  sort(s.toArray)
  val end = System.currentTimeMillis()
  println("total: " + (end - start))

}
