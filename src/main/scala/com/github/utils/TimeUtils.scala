package com.github.utils

import java.text.{ParseException, SimpleDateFormat}

/**
  * Created by zk_chs on 16/4/12.
  */
object TimeUtils {

  private val simpleDateFormat = new SimpleDateFormat()

  @throws(classOf[ParseException])
  def dateParse (time: String) = {
    simpleDateFormat applyPattern "yyyy-MM-dd"
    simpleDateFormat parse time
  }

}
