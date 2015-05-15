package yp.projects.state

import scalaz._
import Scalaz._

/**
 * Created by yeghishe on 2/10/15.
 */
/*
sealed trait Metrics {
  def set(name: String, value: Int): Unit
  def get(name: String): Option[Int]
  def inc(name: String): Unit
  def incBy(name: String, value: Int): Unit
}

object Metrics {
  implicit object InMemoryMetrics extends Metrics {
    private var data: Map[String, Int] = Map.empty

    override def set(name: String, value: Int): Unit = data += name -> value

    override def incBy(name: String, value: Int): Unit =
      data += name -> (data.getOrElse(name, 0) + value)

    override def inc(name: String): Unit = incBy(name, 1)

    override def get(name: String): Option[Int] = data.get(name)
  }
}
*/

/*
case class Data(map: Map[String, Int]) {
  def get(name: String): Option[Int] = map.get(name)
  def set(name: String, value: Int): Data = copy(map + (name -> value))
  def incBy(name: String, value: Int): Data = copy(map + (name -> (map.getOrElse(name, 0) + value)))
  def inc(name: String): Data = incBy(name, 1)
}
*/

sealed trait Metrics {
  type StateMetrics[A] = State[Map[String, Int], A]

  def setValue(name: String, value: Int): StateMetrics[Unit]
  def getValue(name: String): StateMetrics[Option[Int]]
  def inc(name: String): StateMetrics[Unit]
  def incBy(name: String, value: Int): StateMetrics[Unit]
}

object Metrics {
  implicit object InMemoryMetrics extends Metrics {
    override def setValue(name: String, value: Int): StateMetrics[Unit] =
      State { s => (s + (name -> value), ()) }

    override def incBy(name: String, value: Int): StateMetrics[Unit] =
      State { s => (s + (name -> (s.getOrElse(name, 0) + value)), ()) }

    override def inc(name: String): StateMetrics[Unit] =
      incBy(name, 1)

    override def getValue(name: String): StateMetrics[Option[Int]] =
      State { s => (s, s.get(name)) }
  }
}

object DemoMetrics {
  def main(args: Array[String]) {

    //import Metrics.StateMetrics

    val metrics = implicitly[Metrics]
    val listOfStates = List(metrics.setValue("a", 1), metrics.inc("a"), metrics.getValue("a"))
    //val stateOfList = listOfStates.sequence[StateMetrics, ]

    //metrics.getValue("a").

    /*
    val metrics = implicitly[Metrics]

    for {
      s <- metrics.setValue("a", 1)
      x <- s.get
    }
    metrics.setValue("a", 1).
      .get("a")
      .eval(Map.empty)

    println(x)
    */
  }
}
