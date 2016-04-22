package com.github.nichijou

import scala.language.{reflectiveCalls, postfixOps}
import scala.reflect.runtime.universe._

/**
  * Created by zk_chs on 16/4/19.
  */
object Test extends App {

  def typeVariable[T <% Comparable[T]](a: T, b: T) = {
    if (a.compareTo(b) > 0) a
    else b
  }

  val v1 = typeVariable("scala", "java")
  println(v1)
  val v2 = typeVariable(100, 200)
  println(v2)

  class A
  class B extends A

  def lowerBound[T, K](a: T, b: K)(implicit v: T <:< K) = println("ok")

//  lowerBound(new A, new B)
  lowerBound(new B, new A)

  class Person(val age: Int) {
    println("person==> " + age)
  }

  class PersonOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = {
      if (x.age > y.age) 1 else -1
    }
  }

  class Pair[T: Ordering](val first: T, val second: T) {
    def old(implicit ord: Ordering[T]) = {
      if (ord.compare(first, second) > 0) first else second
    }
  }

  implicit val po = new PersonOrdering
  val p = new Pair(new Person(18), new Person(19))
  println(p.old.age)

  val p1 = new Person(123)
  val p2 = new Person(123)
  println(p1 == p2)
  println(p1 eq p2)
  println(p1 equals p2)
  println("p1->" + p1.hashCode() + "|||p2->" + p2.hashCode())

  //定义一个普通的scala类，其中包含了close成员方法
  class File{
    def close(): Unit = println("File Closed")
  }
  //定义一个单例对象，其中也包含了close成员方法
  object File{
    def close(): Unit = println("object File closed")
  }

  def releaseMemory(res: {def close(): Unit}){
    res close()
  }
  type X = {def close(): Unit}
  def releaseMemory2(x: X) = x close()

  releaseMemory(new {def close() = println("closed")})
  releaseMemory2(new {def close() = println("closed")})

  //对于普通的scala类，直接创建对象传入即可使用前述的方法
  releaseMemory(new File)
  //对于单例对象，直接传入单例对象即可
  releaseMemory(File)

  def print2(x:Array[T] forSome {type T})=println(x)
  def print(x:Array[_])=println(x)

}
