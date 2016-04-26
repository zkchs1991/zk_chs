package com.github.nichijou.demo

import scala.language.implicitConversions

/**
  * Created by zk_chs on 16/4/19.
  * 多重界定
  */
//noinspection NoReturnTypeForImplicitDef,DeprecatedViewBound
object MultiBound extends App {

  class A[T]
  class B[T]

  implicit val a = new A[String]
  implicit val b = new B[String]
  //多重上下文界定，必须存在两个隐式值，类型为A[T],B[T]类型
  //前面定义的两个隐式值a,b便是
  def test[T: A: B](x: T) = println(x)
  test("zk_chs")

  implicit def t2A[T](x: T) = new A[T]
  implicit def t2B[T](x: T) = new B[T]
  //多重视图界定，必须存在T到A，T到B的隐式转换
  //前面我们定义的两个隐式转换函数就是
  def test2[T <% A[T] <% B[T]](x: T) = println(x)
  test2("zk_chs")

}
