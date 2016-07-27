package com.github.nichijou.fromblog

import scala.reflect._
import scala.reflect.runtime.universe._

class Animal{}
class Bird extends Animal{}
class Hummingbird extends Bird{}

class Consumer[-S, +T]()(implicit m1: ClassTag[T]) {
  /** U以协变类型T为下界,间接地在逆变点使用了协变类型T */
  def m1[U >: T](u: U): T = {m1.runtimeClass.newInstance.asInstanceOf[T]} //协变，下界
  /** U以逆变类型S为上界,间接地在协变点使用了逆变类型S */
  def m2[U <: S](s: S)(implicit m2: ClassTag[U]): U = {m1.runtimeClass.newInstance.asInstanceOf[U]} //逆变，上界
}

object Test extends App {
  val c: Consumer[Animal, Bird] = new Consumer[Animal, Bird]()
  val c2: Consumer[Bird, Animal] = c
  c2.m1(new Animal)
  c2.m2(new Bird)
}