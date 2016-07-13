package com.github.akka.fromcsdn

import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.{ActorSystem, Props, Actor}
import akka.event.Logging
import akka.pattern.ask
import akka.pattern.pipe
import akka.util.Timeout

import scala.concurrent.Future

/**
  * Created by zk_chs on 16/7/13.
  */
object Example_10 extends App{



  // 消息：个人基础信息
  case class BasicInfo(id: Int, val name: String, age: Int)
  // 消息：个人兴趣信息
  case class InterestInfo(id: Int, val interest: String)
  // 消息: 完整个人信息
  case class Person(basicInfo: BasicInfo, interestInfo: InterestInfo)

  // 基础信息对应Actor
  class BasicInfoActor extends Actor {
    val log = Logging(context.system, this)
    def receive = {
      // 处理送而来的用户ID，然后将结果发送给sender（本例中对应CombineActor）
      case id: Int => log.info("id = " + id); sender! new BasicInfo(id, "zk_chs", 15)
      case _       => log.info("receive unknown message")
    }
  }

  // 兴趣爱好对应Actor
  class InterestInfoActor extends Actor {
    val log = Logging(context.system, this)
    def receive = {
      // 处理送而来的用户ID，然后将结果发送给sender（本例中对应CombineActor）
      case id: Int => log.info("id = " + id); sender! new InterestInfo(id, "动画")
      case _       => log.info("receive unknown message")
    }
  }

  // Person完整信息对应Actor
  class PersonActor extends Actor {
    val log = Logging(context.system, this)
    def receive = {
      case person: Person => log.info("Person = " + person)
      case _       => log.info("receive unknown message")
    }
  }


  class CombineActor extends Actor {
    implicit val timeout = Timeout(5, TimeUnit.SECONDS)
    val basicInfoActor = context.actorOf(Props[BasicInfoActor], name = "BasicInfoActor")
    val interestInfoActor = context.actorOf(Props[InterestInfoActor], name = "InterestInfoActor")
    val personActor = context.actorOf(Props[PersonActor], name = "PersonActor")

    def receive = {
      case id: Int =>
        val combineResult: Future[Person] =
          for {
            // 向basicInfoActor发送Send-And-Receive-Future消息，mapTo方法将返回结果映射为BasicInfo类型
            basicInfo <- ask(basicInfoActor, id).mapTo[BasicInfo]
            // 向interestInfoActor发送Send-And-Receive-Future消息，mapTo方法将返回结果映射为InterestInfo类型
            interestInfo <- ask(interestInfoActor, id).mapTo[InterestInfo]
          } yield Person(basicInfo, interestInfo)

        // 将Future结果发送给PersonActor
        pipe(combineResult).to(personActor)
    }

  }

  val system = ActorSystem("Send-And-Receive-Future")
  val combineActor = system.actorOf(Props[CombineActor], name = "CombineActor")

  combineActor! 12345
  Thread.sleep(5000)
  system.terminate

}
