package com.github.concurrentPrac.exercise.ch1

/**
  * Created by zk_chs on 16/7/13.
  */
object Ex1 extends App{

  def compose[A, B, C](g: B => C, f: A => B): A => C = x => g(f(x))

  def compose2[A, B, C](g: B => C, f: A => B): A => C = g compose f

}
