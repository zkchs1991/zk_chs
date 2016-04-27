package com.github.akka.fromcsdn

import akka.actor._

/**
  * Created by zk_chs on 16/4/17.
  * 停止Actor:使用akka.actor.PoisonPill
  */
object Example_08 extends App {

  class FirstActor extends Actor with ActorLogging{

    var child:ActorRef = context.actorOf(Props[MyActor], name = "myActor")
    def receive = {
      //向child发送PoisonPill停止其运行
      case "stop"=>child ! PoisonPill
      case x =>{
        //向MyActor发送消息
        child ! x
        log.info("received "+x)
      }

    }
    override def postStop(): Unit = {
      log.info("postStop In FirstActor")
    }
  }

  class MyActor extends Actor with ActorLogging{
    def receive = {
      case "test" => log.info("received test");
      case _      => log.info("received unknown message");
    }
    override def postStop(): Unit = {
      log.info("postStop In MyActor")
    }
  }
  val system = ActorSystem("MyActorSystem")
  val systemLog=system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  systemLog.info("准备向firstactor发送消息")
  //向firstactor发送消息
  firstactor ! "test"
  firstactor ! 123
  firstactor ! "stop"

}
