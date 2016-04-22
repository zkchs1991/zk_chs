package com.github.nichijou

/**
  * Created by zk_chs on 16/4/23.
  */
object HigherMethod extends App {

  def convertInt2String(x: Int): Int => String = (m: Int) => x * m + "" // convertInt2String: (x: Int)Int => String
  val resultMethod = convertInt2String(10) // 生成另外一个函数 def mid(m: Int) = 10 * m + ""
  val result = resultMethod(20) // 200

}
