package io.github.yeghishe.scalazsnippets

import scalaz.Scalaz._
import scalaz._

object DisjunctionSnippets extends App {
  case class User(name: String, age: Int)
  class DbConnectionException(message: String) extends RuntimeException(message)
  class DbStatementException(message: String) extends RuntimeException(message)
  class DbConnection

  def getConnection(connectionString: String, i: Int): DbConnection = {
    if (i == 1) throw new DbConnectionException("Can't connect to db.")

    new DbConnection
  }

  def runQuery(connection: DbConnection, query: String, i: Int): Boolean = {
    if (i == 2) throw new DbStatementException("Statement error")

    true
  }

  def saveUser(user: User, i: Int): Throwable \/ Boolean = {
    for {
      connection <- \/.fromTryCatchNonFatal(getConnection("", i))
      result <- \/.fromTryCatchNonFatal(runQuery(connection, "", i))
    } yield result
  }

  val y = User("Yeghishe", 18)

  Seq(
    saveUser(y, 0),
    saveUser(y, 1),
    saveUser(y, 2)
  ).foreach(println)
}

object DisjunctionSnippets2 extends App {
  case class Response(statusCode: Int, responseBody: String)

  def callThirdPartyService(token: String): Response = {
    token match {
      case "good" => Response(200, "useful data")
      case "bad"  => Response(401, "bad token")
      case _      => Response(500, "error")
    }
  }

  def getData(token: String): Int ∨ String = {
    callThirdPartyService(token) match {
      case Response(200, d)    => d.right
      case Response(status, _) => status.left
    }
  }

  Seq(
    getData("good"),
    getData("bad"),
    getData("foo")
  ).foreach(println)
}
