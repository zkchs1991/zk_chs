package com.github.akka.fromcsdn

import akka.actor._

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/16.
  */
object Example_06 extends App{

  class FirstActor extends Actor with ActorLogging{
    //通过context.actorOf方法创建Actor
    var child:ActorRef = _
    override def preStart(): Unit ={
      log.info("preStart() in FirstActor")
      //通过context上下文创建Actor
      child = context.actorOf(Props[MyActor], name = "myActor")
    }
    def receive = {
      //向MyActor发送消息
      case x => child ! x;log.info("received "+x)
    }
  }

  class MyActor extends Actor with ActorLogging{
    var parentActorRef:ActorRef=_
    override def preStart(): Unit ={
      //通过context.parent获取其父Actor的ActorRef
      parentActorRef = context.parent
    }
    def receive = {
      case "test" => log.info("received test"); parentActorRef! "message from ParentActorRef"
      case _      => log.info("received unknown message");
    }

  }

  val system = ActorSystem("MyActorSystem")
  val systemLog = system.log

  //创建FirstActor对象
  val myactor = system.actorOf(Props[FirstActor], name = "firstActor")
  //获取ActorPath
  val myActorPath = system.child("firstActor")
  //通过system.actorSelection方法获取ActorRef
  val myActor1 = system.actorSelection(myActorPath)
  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  myActor1! "test"
  myActor1! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.terminate

}
