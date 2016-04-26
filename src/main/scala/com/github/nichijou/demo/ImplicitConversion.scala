package com.github.nichijou.demo

import com.github.nichijou.demo.ScalaTest.OutputFormat

import scala.language.implicitConversions

/**
  * Created by zk_chs on 16/4/22.
  * 可分为隐式转换函数,隐式转换参数
  */
object ImplicitConversion extends App {

  // 隐式转换函数
  implicit def str2Int(x: String) = x.toInt
  // 不能使用下面的这种形式,添加了返回值会发生报错
//  implicit def strToInt(x: String): Int = x.toInt
  val i: Int = "123"
  println(i)

  implicit def double2Int(x: Double): Int = x.toInt
  val j: Int = 3.5
  println(j)

  // 隐式转换参数
  class Student(var name: String){
    def formatStudent(implicit outputFormat: OutputFormat) = {
      outputFormat.first + this.name + outputFormat.second
    }
  }
  implicit val outputFormat = new OutputFormat("<<", ">>")
  println(new Student("zk_chs").formatStudent)

  implicit val x: Int = 0
  def total(a: Int)(implicit b: Int) = a + b
  println(total(3)(0)) // 3
  println(total(3)) // 3

}
