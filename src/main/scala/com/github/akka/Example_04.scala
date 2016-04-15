package com.github.akka

import akka.actor._

/**
  * Created by zk_chs on 16/4/15.
  */
object Example_04 extends App{

  class FirstActor extends Actor with ActorLogging {

    // 通过context.actorOf方法创建Actor
    var child: ActorRef = _

    // Hook方法,preStart()方法,Actor启动前调用,用于完成初始化工作
    @throws[Exception](classOf[Exception])
    override def preStart(): Unit = {
      log.info("preStart() in FirstActor")
      // 通过上下文创建Actor
      child = context.actorOf(Props[MyActor], name = "myChild")
    }

    override def receive: Receive = {
      // 向myActor发送消息
      case x => child ! x; log.info("received " + x)
    }

  }

  class MyActor extends Actor with ActorLogging {

    self ! "message from self reference"
    override def receive: Actor.Receive = {
      case "test" => log.info("received test");sender() ! "message from MyActor"
      case "message from self reference" => log.info("message from self reference")
      case _      => log.info("received unknown message")
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  // 创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向myactor发送消息")
  // 向myactor发送消息
  myactor ! "test"
  myactor ! 123
  Thread.sleep(5000)

  // 关闭ActorSystem,停止程序的运行
  system.terminate()

}
