package com.github.nichijou.demo

/**
  * Created by zk_chs on 16/4/24.
  */
object FlatAndFold extends App {

  println(List.range(1,2)) // 不会取到2

  val data = List("scala", "java", "hadoop", "spark")
  println(data.flatMap(_.toList))

  println((1 to 100).foldLeft(0)(_ + _)) // 5050
  println((0 /: (1 to 100))(_ + _)) // 5050


  /** 模仿 /: 方法带两个参数的写法 */
  class Test[B] {
    def -: (z: B)(m: B): B = test(z)(m)
    def test(x: B)(y: B): B = {
      println(x + "    " + y)
      x
    }
  }
  val t = new Test[Int]
  t.test(1)(2)

  t.-:(1)(2) // 柯里化的写法
  (1 -: t)(2) // 中置表达式类型写法

}
