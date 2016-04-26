package com.github.nichijou.demo

/**
  * Created by zk_chs on 16/4/23.
  * case class, 模式匹配
  */
object PatternMatch extends App {

  sealed abstract class Person
  case class Teacher(name: String, age: Int) extends Person
  case class Student(name: String, age: Int, grade: Int) extends Person
  case class Nobody(name: String) extends Person

  val person: Person = new Teacher("zk_chs", 17)

  def patternMatch(x: Any) = x match {
    case Teacher(_, _)                    => 1
    case Student(_, _, _)                 => 0
    case Nobody(_)                        => -1
    case i: Int                           => i // 匹配传入的参数的类型
    case e @ List(_, m @ List(_, _, _))   => scala.Tuple2(e, m) // 将ListList(_, List(_, _, _))绑定到e上,List(_, _, _)绑定在m上
    case List(_, second, _*)              => second // 只需要匹配第二个元素
    case y                                => y // 所有不是上面匹配项的值都会匹配y
  }
  println(patternMatch(new Teacher("zk_chs", 17)))
  println(patternMatch(998)) // 998
  println(patternMatch(List(1, List(88, 98, 998)))) // (List(1, List(88, 98, 998)),List(88, 98, 998))
  println(patternMatch(List(1, new Student("zk_chs", 17, 1), 3, 4))) // Student(zk_chs,17,1)
  println(patternMatch("xxx")) // 输出xxx

  val map = Map("scala" -> 1, "java" -> 2, "zk_chs" -> 998)
  def mapMatch(t: String) = map.get(t) match {
    case Some(x)          => println(x); x
    case None             => println("None"); -1
  }
  mapMatch("zk_chs") // 输出998
  mapMatch("php") // None

}
