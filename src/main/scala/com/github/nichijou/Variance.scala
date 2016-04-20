package com.github.nichijou

/**
  * Created by zk_chs on 16/4/20.
  * 协变与逆变
  * 关键在于,父类能调用的方法,子类也能调用
  */
object Variance extends App {

  class List[+T](val head: T, val tail: List[T]) {
    //下面的方法编译会出错
    //covariant type T occurs in contravariant position in type T of value newHead
    //编译器提示协变类型T出现在逆变的位置
    //即泛型T定义为协变之后，泛型便不能直接
    //应用于成员方法当中
//    def prepend(newHead: T): List[T] = new List(newHead, this)

    //将函数也用泛型表示
    //因为是协变的，输入的类型必须是T的超类
    def prepend[U >: T](newHead: U): List[U] = new List(newHead, this)
    override def toString = "" + head + "---" + tail
  }

  val list: List[Any] = new List[String]("String", new List[String]("123", null))
  println(list)

  //声明逆变
  class Person2[-A]{
    // 逆变点为参数x: A.子类中函数参数的必须是父类中函数参数的超类，这样的话父类能做的子类也能做
    def test(x: A) = {}
  }
  var pAnyRef2 = new Person2[AnyRef]
  var pString2= new Person2[String]
  pString2 = pAnyRef2
  pString2 test "123"

  //声明协变，但会报错
  //covariant type A occurs in contravariant position in type A of value x
  class Person3[+A]{
    /** 假设该方法通过编译,那么pAnyref3 = pString3之后,继续调用pAnyref3.test(123)便会报错
      * 因为pString3.test的参数为String类型,但pAnyref3的test方法参数类型为Anyref类型
      * 这样一来,pAnyref3 = pString3之后执行pAnyref3.test(123)会报错,因为实际运行时是pString3在运行
      */
//    def test(x: A) = {}
  }
  var pAnyref3 = new Person3[AnyRef]
  var pString3 = new Person3[String]
  pAnyref3 = pString3

  //下面这行代码能够正确运行
  class Person4[+A] {
    def test: A = null.asInstanceOf[A]
  }
  var pAnyref4 = new Person4[AnyRef]
  var pString4 = new Person4[String]
  pAnyref4 = pString4
  println(pAnyref4.test)

  //下面这行代码会编译出错
  //contravariant type A occurs
  //in covariant position in type ⇒ A of method test
  class Person5[-A] {
    /** 假设该方法通过编译,那么pString5 = pAnyref5之后,继续调用pString5.test便会报错
      * 因为pAnyref5.test返回Anyref,而pString5作为父类,返回值为String
      * 在pString5 = pAnyref5之后,调用pString5.test的话,返回的其实是Anyref,与pString5.test应有的返回值String不匹配,发生报错
      */
//    def test: A = null.asInstanceOf[A]
  }
  var pAnyref5 = new Person5[AnyRef]
  var pString5 = new Person5[String]
  pString5 = pAnyref5

}
