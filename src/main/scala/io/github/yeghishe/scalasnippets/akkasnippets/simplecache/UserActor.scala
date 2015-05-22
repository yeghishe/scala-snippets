package io.github.yeghishe.scalasnippets.akkasnippets.simplecache

import akka.actor.{ Actor, ActorRef, Props }
import akka.util.Timeout

object UserActor {
  def props(userId: Int, dataActorRef: ActorRef): Props = Props(new UserActor(userId, dataActorRef))
  case object GetUserData
  case object GetUserCredits
}

class UserActor(userId: Int, dataActorRef: ActorRef) extends Actor with ActorCaching {
  import UserActor._
  import akka.pattern.ask

  implicit private val timeout: Timeout = askTimeout

  override def receive: Receive = super.receive orElse {
    case msg @ GetUserData ⇒
      log.info("Getting user data")
      cacheAndRespond(msg, dataActorRef ? DataActor.GetUserData(userId))
    case GetUserCredits ⇒
      log.info("Getting user credits")
      respond(dataActorRef ? DataActor.GetUserCredits(userId))
  }
}
