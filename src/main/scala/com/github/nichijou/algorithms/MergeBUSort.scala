package com.github.nichijou.algorithms

import com.github.nichijou.algorithms.MergeSort._

/**
  * Created by zk_chs on 16/4/27.
  * 归并排序,自底向上,比较适合连标组织的数据
  */
object MergeBUSort extends Example2[Char]{

  var aux: Array[Char] = _

  override def sort(a: Array[Char]) = {
    aux = new Array[Char](length)
    var sz = 1
    while (sz <= length - 1){ // sz:子数组大小
      for (lo <- 0 until length - sz by 2*sz) // lo:子数组索引
        merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, length-1))
      sz = 2 * sz
    }
//    for (sz <- 1 until length - 1 )
//      for (lo <- sz until length - 1 by 2*sz)
//        merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, length-1))
    show(a)
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

  val s = "asmnqoakqwnkdksfpqpowqenvsjhsakewqncammxnckjehuiuqwnejkasjncsjakbdqwijncajxkajdkqjwhejnjkxcnajkfbocviuwetiyu"
  val length = s.length
  println(s.toArray.mkString(" "))
  val start = System.currentTimeMillis()
  sort(s.toArray)
  val end = System.currentTimeMillis()
  println("total: " + (end - start))

}
