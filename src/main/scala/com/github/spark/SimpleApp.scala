package com.github.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zk_chs on 16/7/27.
  */
object SimpleApp {

  def main(args: Array[String]): Unit = {
    val logFile = "/Users/zk_chs/something/spark/README.md" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }

}
