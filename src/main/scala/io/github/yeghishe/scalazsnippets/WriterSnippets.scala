package io.github.yeghishe.scalazsnippets

/**
 * Created by yeghishe on 2/13/15.
 */

import scalaz.Scalaz._
import scalaz._

object WriterSnippets {
  case class Customer(id: Int, name: String)
  case class Order(id: Int, name: String, customerId: Int)

  val customers = List(Customer(1, "customer1"), Customer(2, "customer2"), Customer(3, "customer3"))
  val orders = List(Order(1, "b1", 1), Order(2, "b2", 2))

  def findOrdersForCustomer(customerId: Int): Writer[Vector[String], List[Order]] = {
    customers.find(_.id ≟ customerId).fold(nil[Order].set(Vector("customer not found"))) { u ⇒
      orders.filter(_.customerId ≟ u.id).set(Vector())
    }
  }

  def main(args: Array[String]): Unit = {
    1 to 4 foreach { customerId =>
      val res = findOrdersForCustomer(customerId)
      val orders = res.value

      res.written.foreach(l => println(s"LOG $l"))

      println(s"burners for user $customerId $orders")
    }
  }
}
