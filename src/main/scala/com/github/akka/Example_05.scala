package com.github.akka

import akka.actor.{Props, ActorSystem, ActorLogging, Actor}

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/15.
  * unhandled方法
  */
object Example_05 extends App{

  class FirstActor extends Actor with ActorLogging {

    override def receive: Receive = {
      case "test" => log.info("received test")
    }

    override def unhandled(message: Any): Unit = {
      log.info("unhandled message is {}", message)
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向myactor发送消息")
  //向myactor发送消息
  myactor!"test"
  myactor! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system terminate

}
