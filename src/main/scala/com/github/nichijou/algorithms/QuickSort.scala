package com.github.nichijou.algorithms

/**
  * Created by zk_chs on 16/4/28.
  */
object QuickSort extends Example[Char]{

  override def sort(a: Array[Char]) = {
    sort(a, 0, a.length - 1)
  }

  def sort(a: Array[Char], lo: Int, hi: Int):Unit = {
    if (lo < hi){
      var i = lo
      var j = hi
      val x = a(i)
      while (i < j){
        while (i < j && less(x, a(j)))
          j -= 1
        if (i < j){
          a(i) = a(j)
          i += 1
        }
        while (i < j && less(a(i), x))
          i += 1
        if (i < j){
          a(j) = a(i)
          j -= 1
        }
      }
      a(i) = x
      sort(a, lo, i - 1)
      sort(a, i + 1, hi)
    }
  }

  val s = "asmnqoakqwnkdksfpqpowqenvsjhsakewqncammxnckjehuiuqwnejkasjncsjakbdqwijncajxkajdkqjwhejnjkxcnajkfbocviuwetiyu"
  val length = s.length
  val arr = s.toArray
  println(arr.mkString(" "))
  val start = System.currentTimeMillis()
  sort(arr)
  val end = System.currentTimeMillis()
  println(arr.mkString(" "))
  println("total: " + (end - start))

}
