package com.github.nichijou

import scala.collection.immutable.Queue
import scala.language.{postfixOps, implicitConversions}
import scala.util.Random

/**
  * Created by zk_chs on 16/4/18.
  */
object ScalaTest extends App{

  // stackoverflow一个问题 http://stackoverflow.com/questions/36684490/how-to-duplicate-two-digits-in-scala
  val chars = 'a' to 'z'
  val perms = chars flatMap { a =>
    chars flatMap { b =>
      if (a != b) Seq("%c%c".format(a, b))
      else Seq()
    }
  }
  println(perms mkString ", ")

  val list1 = new Array[String](0) // Array()
  val list2 = new Array[String](3) // Array(null, null, null)
  val list3:Array[String] = new Array(3) // // Array(null, null, null)

  def duplicateDigits(xs: List[Int]): List[Long]= xs match {
    case Nil => Nil
    case x::ys  => x.toLong :: x.toLong :: duplicateDigits(ys)
  }
  val list = duplicateDigits(List(1,2,3,4,5,6,7,8,9,10,11,345))
  println(list mkString ", ")

  val xs = List(1,2,3,4,5,6,7,8,9,10,11,345)
  println(xs.zip(xs).flatMap(pair => List(pair._1,pair._2)))

  // 定义固定长度的List
  println(List.fill(10)(13))
  println(List.fill(10)(Random.nextInt(101)))

  // 下面3个相同
  println(Range(0, 5))
  println(0 until 5)
  println(0 to 4)

  // queue
  println(Queue(0, 10, 20, 30).dequeue) // 返回一个tuple (0, Queue(10, 20, 30))

  // Stream,相当于lazy list,避免在中间过程中生成不必要的集合
  val st = 1 #:: 2 #:: 3 #:: Stream.empty // Stream(1, ?)
  print(st + "\n")
  st.foreach(println)
  print(st + "\n")
  // fib数列
  def fib(a: Int, b: Int): Stream[Int] = a #:: fib(b,  a+b)
//  fib.foreach(println) //报错 Null Pointer  因为是方法,不能调用
  val fibs = fib(1, 1).take(7).toList // List(1, 1, 2, 3, 5, 8, 13)
  println(fib(1, 1)(6)) // 13
  println(fibs)
  //  fib数列的前后项比值趋于黄金分割
  def fn(n: Int) = fib(1, 1)(n)
  println(1 to 10 map (n=> 1.0 * fn(n) / fn(n+1)))// Vector(0.5, 0.666, ..., 0.618)

  // 隐式转换===>隐式转换函数
  implicit def double2Int(x: Double): Int = x.toInt
  def f(x: Int) = x
  println(f(3.9))

  // 隐式转换===>隐式参数
  class Student(var name:String){
    //利用柯里化函数的定义方式，将函数的参数利用
    //implicit关键字标识
    //这样的话，在使用的时候可以不给出implicit对应的参数
    def formatStudent()(implicit outputFormat: OutputFormat)={
      outputFormat.first + " " + this.name + " " + outputFormat.second
    }
  }
  class OutputFormat(var first: String,val second: String)
  //程序中定义的变量outputFormat被称隐式值
  implicit val outputFormat=new OutputFormat("<<",">>")
  //在.formatStudent()方法时，编译器会查找类型
  //为OutputFormat的隐式值,本程序中定义的隐式值
  //为outputFormat
  println(new Student("john") formatStudent)

}
