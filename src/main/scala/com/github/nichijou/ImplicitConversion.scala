package com.github.nichijou

import com.github.nichijou.ScalaTest.OutputFormat

import scala.language.implicitConversions

/**
  * Created by zk_chs on 16/4/22.
  * 可分为隐式转换函数,隐式转换参数
  */
object ImplicitConversion extends App {

  // 隐式转换函数
  implicit def doubleToInt(x: Double): Int = x.toInt
  val i: Int = 3.5
  println(i)

  // 隐式转换参数
  class Student(var name: String){
    def formatStudent(implicit outputFormat: OutputFormat) = {
      outputFormat.first + this.name + outputFormat.second
    }
  }
  implicit val outputFormat = new OutputFormat("<<", ">>")
  println(new Student("zk_chs").formatStudent)


}
