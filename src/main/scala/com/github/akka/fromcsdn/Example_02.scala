package com.github.akka.fromcsdn

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

/**
  * Created by zk_chs on 16/4/15.
  */
object Example_02 extends App {

  class FirstActor extends Actor with ActorLogging {
    val child = context.actorOf(Props[MyActor], name = "myChild")
    override def receive: Actor.Receive = {
      case x => child ! x; log.info("received " + x)
    }
  }

  class MyActor extends Actor with ActorLogging{
    override def receive: Receive = {
      case "test" => log.info("received test")
      case _      => log.info("received unknown message")
    }
  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  // 创建MyActor,指定actor名称为myactor
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向myactor发送消息")
  // 向myactor发送消息
  myactor ! "test"
  myactor ! 123
  Thread.sleep(5000)

  // 关闭ActorSystem,停止程序的运行
  system terminate

}