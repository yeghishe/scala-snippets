package io.github.yeghishe.scalasnippets.akkasnippets.simplecache.io.github.yeghishe.scalasnippets.scalatestpost

import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class User(id: Option[Int], name: String)

trait FacebookClient {
  /**
   * Gets user info from facebook.
   * @param token The facebook token.
   * @return Returns User if token is valid, None otherwise.
   */
  def getUserInfo(token: String): Future[Option[User]]
}

trait Db {
  /**
   * Save user.
   * @param user The user.
   * @return User if it's able to save to db, None otherwise.
   */
  def saveUser(user: User): Future[Option[User]]

  /**
   * Finds user by user id.
   * @param id The user id.
   * @return User if user is found, None otherwise.
   */
  def findUserById(id: Int): Future[Option[User]]
}

class UserManager(facebookClient: FacebookClient, db: Db) {
  def createUser(token: String): Future[Option[User]] = for {
    fu <- facebookClient.getUserInfo(token)
    dbu <- fu.map(db.saveUser).getOrElse(Future(None))
  } yield dbu

  def getUser(id: Int): Future[Option[User]] = db.findUserById(id)
}


class UserManagerTest extends WordSpec with Matchers with ScalaFutures with MockFactory {
  val facebookClient = mock[FacebookClient]
  val db = mock[Db]
  val userManager = new UserManager(facebookClient, db)

  "The user manager" when {
    val userId = 1
    val user = User(None, "some user")
    val dbUser = user.copy(id = Some(userId))

    "createUser" should {
      val token = "token"

      "call db to save user and return saved user when token is a valid facebook token" in {
        // Arrange
        (facebookClient.getUserInfo _).expects(token) returning Future(Some(user))
        (db.saveUser _).expects(user) returning Future(Some(dbUser))

        // Act and Assert
        userManager.createUser(token).futureValue should be(Some(dbUser))
      }

      "NOT call db if token isn't valid and return None" in {
        // Arrange
        (facebookClient.getUserInfo _).expects(token) returning Future(None)

        // Act and Assert
        userManager.createUser(token).futureValue should be(None)
      }

      "return None if unable to save to db" in {
        // Arrange
        (facebookClient.getUserInfo _).expects(token) returning Future(Some(user))
        (db.saveUser _).expects(user) returning Future(None)

        // Act and Assert
        userManager.createUser(token).futureValue should be(None)
      }
    }

    "getUser" should {
      "return user if user is found in db" in {
        // Arrange
        (db.findUserById _).expects(userId) returning Future(Some(dbUser))

        // Act and Assert
        userManager.getUser(userId).futureValue should be(Some(dbUser))
      }

      "return None user isn't found in db" in {
        // Arrange
        (db.findUserById _).expects(userId) returning Future(None)

        // Act and Assert
        userManager.getUser(userId).futureValue should be(None)
      }
    }
  }
}
