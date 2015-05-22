package io.github.yeghishe.scalasnippets.akkasnippets.simplecache

import akka.actor._
import scala.concurrent.duration._

object DriverActor {
  def props(userActor: ActorRef): Props = Props(new DriverActor(userActor))
}

class DriverActor(userActor: ActorRef) extends Actor with ActorLogging {
  import UserActor._
  private case object Work

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    import context.dispatcher
    context.system.scheduler.schedule(Duration.Zero, FiniteDuration(10, SECONDS), self, Work)
  }

  override def receive: Receive = {
    case Work ⇒
      log.info("Calling to get user data")
      userActor ! GetUserData
    case msg: DataActor.UserData ⇒ log.info(s"got $msg")
  }
}

object Main extends App {
  val system = ActorSystem("simple-cache-demo-system")
  val userId = 10

  val dataActor = system.actorOf(DataActor.props, "data")
  val userActor = system.actorOf(UserActor.props(userId, dataActor), s"user-$userId")
  system.actorOf(DriverActor.props(userActor), "driver")
}
