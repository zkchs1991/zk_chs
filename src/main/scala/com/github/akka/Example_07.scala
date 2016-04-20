package com.github.akka

import akka.actor._

import scala.language.postfixOps

/**
  * Created by zk_chs on 16/4/16.
  */
object Example_07 extends App {

  class FirstActor extends Actor with ActorLogging{
    //通过context.actorOf方法创建Actor
    var child: ActorRef = _
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
    def receive = {
      case "test" => log.info("received test");
      case _      => log.info("received unknown message");
    }

  }
  val system = ActorSystem("MyActorSystem")
  val systemLog=system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  //获取ActorPath
  val myActorPath=system.child("firstActor").child("myActor")
  systemLog.info("firstActorPath--->{}",myActorPath)


  //通过system.actorSelection方法获取ActorRef
  val myActor1=system.actorSelection(myActorPath)
  systemLog.info("myActor1 => {}", myActor1.pathString)

  //直接指定其路径
  val myActor2=system.actorSelection("/user/firstActor/myActor")
  systemLog.info("myActor2 => {}", myActor2.pathString)
  //使用相对路径,查找同一父监管者下的兄弟
  val myActor3=system.actorSelection("../firstActor/myActor")
  systemLog.info("myActor3 => {}", myActor3.pathString)


  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  myActor1 ! "test"
  myActor1 ! 123
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system terminate

}
