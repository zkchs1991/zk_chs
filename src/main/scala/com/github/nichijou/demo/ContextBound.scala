package com.github.nichijou.demo

import scala.Ordered.orderingToOrdered

/**
  * Created by zk_chs on 16/4/19.
  * ContextBound  上下文界定
  * 类型变量界定、视图界定都是将泛型限定在一定范围内，而上下文界定则是将类型限定为某一类型
  */
object ContextBound extends App {

  //PersonOrdering混入了Ordering，它与实现了Comparator接口的类的功能一致
  class PersonOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = {
      if(x.name > y.name)
        1
      else
        -1
    }
  }

  case class Person(val name: String) {
    println("正在构造对象:" + name)
  }

  //下面的代码定义了一个上下文界定
  //它的意思是在对应作用域中，必须存在一个类型为Ordering[T]的隐式值，该隐式值可以作用于内部的方法
  class Pair[T: Ordering](val first: T,val second: T) {
    //smaller方法中有一个隐式参数，该隐式参数类型为Ordering[T]
    def smaller(implicit ord: Ordering[T]) = {
      if(ord.compare(first, second) > 0)
        first
      else
        second
    }
  }

  implicit val p1 = new PersonOrdering
  val p = new Pair(Person("123"), Person("456"))
  // 不给函数指定参数，此时会查找一个隐式值，该隐式值类型为Ordering[Person],根据上下文界定的要求，该类型正好满足要求
  // 因此它会作为smaller的隐式参数传入，从而调用ord.compare(first, second)方法进行比较
  println(p.smaller)

}

/** ContextBound2 */
object ContextBound2 extends App {

  class PersonOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person): Int = {
      if(x.name > y.name)
        1
      else
        -1
    }
  }

  case class Person(val name: String) {
    println("正在构造对象:"+name)
  }

  class Pair[T: Ordering](val first: T, val second: T) {
    //引入odering到Ordered的隐式转换
    //在查找作用域范围内的Ordering[T]的隐式值
    //本例的话是implicit val p1=new PersonOrdering
    //编译器看到比较方式是<的方式进行的时候，会自动进行
    //隐式转换，转换成Ordered，然后调用其中的<方法进行比较
    def smaller = { // 此处有隐式转换,如果和之前一样使用了implicit的话,会报错,而且必须要导入Ordered.orderingToOrdered
      if(first < second)
        first
      else
        second
    }
  }

  implicit val p1 = new PersonOrdering
  val p = new Pair(Person("123"), Person("456"))
  println(p.smaller)

}
