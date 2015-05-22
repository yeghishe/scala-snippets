package io.github.yeghishe.scalasnippets.akkasnippets.simplecache

import akka.actor.{ Props, Actor, ActorLogging }

object DataActor {
  def props: Props = Props(new DataActor)

  case class GetUserData(userId: Int)
  case class UserData(username: String)

  case class GetUserCredits(userId: Int)
  case class UserCredits(credits: Int)
}

class DataActor extends Actor with ActorLogging {
  import DataActor._

  override def receive: Receive = {
    case GetUserData(userId)    ⇒ sender ! UserData(s"user$userId")
    case GetUserCredits(userId) ⇒ sender ! UserCredits(userId)
  }
}
