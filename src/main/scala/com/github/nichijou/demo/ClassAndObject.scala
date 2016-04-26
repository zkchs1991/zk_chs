package com.github.nichijou.demo

/**
  * Created by zk_chs on 16/4/22.
  * 伴生对象与伴生类
  * apply方法的使用
  */
object ClassAndObject extends App {

  class Student (var name: String, var age: Int){
    private var sex: Int = 0
    def printCompanionObject = println(Student.studentNo)
  }

  /** 伴生对象不允许在定义时添加参数,只能在apply方法上增加参数 */
  object Student {
    private var studentNo: Int = 0
    def uniqueStudentNo = {
      studentNo += 1
      studentNo
    }
    def apply (name: String, age: Int) = new Student(name, age)
  }

  val s1 = Student("zhangkai", 17)
  val s2 = Student("zhangkai", 17)
  println(s1 == s2) // false

  class Person {
    def printCompanionObject = println(Person.personNo)
  }

  object Person {
    private var name: String = _
    private var age: Int = _
    private var personNo: Int = 0
    def uniqueStudentNo = {
      personNo += 1
      personNo
    }
    def apply(name: String, age: Int) = {
      this.name = name
      this.age = age
    }
  }

  val p1 = Person("zhangkai", 17)
  val p2 = Person("zhangkai", 17)
  println(p1 == p2) //true

}
