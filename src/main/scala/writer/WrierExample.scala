package writer

/**
 * Created by yeghishe on 2/13/15.
 */

import scalaz._
import Scalaz._

case class User(id:Int, name: String)
case class Burner(id:Int, name: String, userId: Int)

object WriterExample {
  val users = List(User(1, "user1"), User(2, "user2"), User(3, "user3"))
  val burners = List(Burner(1, "b1", 1), Burner(2, "b2", 2))

  def findBurnersForUserV1(userId: Int): List[Burner] = {
    users.find(_.id ≟ userId).fold(nil[Burner]) { u ⇒
      burners.filter(_.userId ≟ u.id)
    }
  }

  def findBurnersForUserV2(userId: Int): Writer[Vector[String], List[Burner]] = {
    users.find(_.id ≟ userId).fold(nil[Burner].set(Vector("user not found"))) { u ⇒
      burners.filter(_.userId ≟ u.id).set(Vector())
    }
  }

  def main(args: Array[String]): Unit = {
    1 to 4 foreach { uId =>
      val res = findBurnersForUserV2(uId)
      val burners = res.value

      res.written.foreach(l => println(s"LOG $l"))

      println(s"burners for user $uId $burners")
    }
  }
}
