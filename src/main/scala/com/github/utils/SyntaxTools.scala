package com.github.utils

import scala.reflect.runtime.universe._
import scala.tools.reflect._

/**
  * Created by zk_chs on 16/4/22.
  */
object SyntaxTools extends App {

  val tb = runtimeMirror(getClass.getClassLoader).mkToolBox()

}
