package io.github.yeghishe.scalazsnippets

import scalaz.Scalaz._
import scalaz._

object ValidationSnippets {

  case class User(name: String, age: Int)

  def validateUser(user: User): ValidationNel[String, User] = {
    val u = user.success
    (if (user.name.isEmpty) "User name can't be empty".failureNel[User] else u) *>
      (if (user.age < 18) "User age should be over 18".failureNel[User] else u) *> u
  }

  def persistUser(user: User): Boolean = { true }

  def createUser(user: User): ValidationNel[String, Boolean] = {
    validateUser(user).map(persistUser)
  }

  def main(args: Array[String]): Unit = {
    Seq(
      createUser(User("", 18)),
      createUser(User("Foo", 17)),
      createUser(User("", 17)),
      createUser(User("Foo", 19))
    ).foreach {
        case Failure(errors) => println(s"Errors: ${errors.foldLeft("")(_ + "\n" + _)}")
        case Success(result) => println(s"SUCCESS $result")
      }
  }
}
