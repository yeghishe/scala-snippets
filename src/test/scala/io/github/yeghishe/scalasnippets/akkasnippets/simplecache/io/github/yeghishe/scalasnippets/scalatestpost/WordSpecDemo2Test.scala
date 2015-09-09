package io.github.yeghishe.scalasnippets.akkasnippets.simplecache.io.github.yeghishe.scalasnippets.scalatestpost.demo2

import akka.actor.{ActorSystem, Props, ActorRef, Actor}
import akka.testkit.{TestProbe, ImplicitSender, TestKit}
import akka.util.Timeout
import org.scalatest.{Matchers, BeforeAndAfterAll, WordSpecLike}
import scala.concurrent.Future
import scala.concurrent.duration._

case class User(id: Option[Int], name: String)

object FacebookActor {
  case class GetUserInfo(token: String)
  case object TokenNotValid
}

object DbActor {
  case class CreateUser(user: User)
}

object UserActor {
  def props(facebookActor: ActorRef, dbActor: ActorRef): Props = Props(new UserActor(facebookActor, dbActor))

  case class FindUserById(id: Int)
  case object UserNotFound
  case class CreateUser(token: String)
  case object CantCreateUser
}

class UserActor(facebookActor: ActorRef, dbActor: ActorRef) extends Actor {
  import akka.pattern.{ ask, pipe }
  import context.dispatcher
  import UserActor._

  implicit private val timeout: Timeout = Duration(10, SECONDS)

  override def receive: Receive = {
    case CreateUser(token) ⇒
      facebookActor.ask(FacebookActor.GetUserInfo(token)).flatMap {
        case user: User ⇒ dbActor.ask(DbActor.CreateUser(user))
        case FacebookActor.TokenNotValid ⇒ Future(CantCreateUser)
      }.pipeTo(self)(sender())

    case msg: FindUserById ⇒ dbActor forward msg
    case CantCreateUser    ⇒ sender ! CantCreateUser
    case msg: User         ⇒ sender ! msg
  }
}

class WordSpecDemo2Test extends TestKit(ActorSystem("test-system"))
    with ImplicitSender
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {
  import UserActor._
  import FacebookActor._

  "The UserActor" when {
    val userId = 1
    val user = User(None, "some user")
    val dbUser = user.copy(id = Some(userId))

    "getting CreateUser message" should {
      val token = "token"

      "respond with CantCreateUser if token isn't valid" in withActors { (userActor, facebookProbe, dbProbe) ⇒
        userActor ! CreateUser(token)

        facebookProbe.expectMsg(GetUserInfo(token))
        facebookProbe.reply(TokenNotValid)

        dbProbe.expectNoMsg()
        expectMsg(CantCreateUser)
      }

      "respond with CantCreateUser if DbActor responds with CantCreateUser" in withActors { (userActor, facebookProbe, dbProbe) ⇒
        userActor ! CreateUser(token)

        facebookProbe.expectMsg(GetUserInfo(token))
        facebookProbe.reply(user)

        dbProbe.expectMsg(DbActor.CreateUser(user))
        dbProbe.reply(CantCreateUser)

        expectMsg(CantCreateUser)
      }

      "respond with user after creating user in db" in withActors { (userActor, facebookProbe, dbProbe) ⇒
        userActor ! CreateUser(token)

        facebookProbe.expectMsg(GetUserInfo(token))
        facebookProbe.reply(user)

        dbProbe.expectMsg(DbActor.CreateUser(user))
        dbProbe.reply(user)

        expectMsg(user)
      }
    }

    "getting FindUserById message" should {
      "respond with user if user is found" in withActors { (userActor, facebookProbe, dbProbe) ⇒
        userActor ! FindUserById(userId)

        dbProbe.expectMsg(FindUserById(userId))
        dbProbe.reply(dbUser)

        expectMsg(dbUser)
      }

      "respond with UserNotFound if user isn't found" in withActors { (userActor, facebookProbe, dbProbe) ⇒
        userActor ! FindUserById(userId)

        dbProbe.expectMsg(FindUserById(userId))
        dbProbe.reply(UserNotFound)

        expectMsg(UserNotFound)
      }
    }
  }

  override protected def afterAll(): Unit = system.shutdown()

  private def withActors(testCode: (ActorRef, TestProbe, TestProbe) ⇒ Any): Unit = {
    val facebookProbe = TestProbe()
    val dbProbe = TestProbe()
    val userActor = system.actorOf(UserActor.props(facebookProbe.ref, dbProbe.ref))

    try {
      testCode(userActor, facebookProbe, dbProbe)
    } finally {
      system.stop(facebookProbe.ref)
      system.stop(dbProbe.ref)
      system.stop(userActor)
    }
  }
}
