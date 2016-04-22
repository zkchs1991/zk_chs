package com.github.nichijou

/**
  * Created by zk_chs on 16/4/22.
  * scala operator: @
  */
object BoundOperator extends App {

  val list = List("a", "b", "c", "d")

  // @操作符,用于模式匹配,若能成功匹配,则将匹配的值赋予变量
  list match {
    case x @ List(_*) => {
      for (num <- x) print(num)
      println
    }
    case _            => println("none")
  }

  val d @ (c @ Some(a), Some(b)) = (Some(1), Some(2))
  println(d)
  println(c)

  (Some(1), Some(2)) match {
    case d @ (c @ Some(a), Some(b)) => println(a, b, c, d)
    case _                          => println("none")
  }

  val x$2 = 100 match {
    case ( aaa @ ( bbb @ _ ) ) => scala.Tuple2(aaa, bbb)
  }
  val aa = x$2._1
  val bb = x$2._2
  println(aa, bb)


}
