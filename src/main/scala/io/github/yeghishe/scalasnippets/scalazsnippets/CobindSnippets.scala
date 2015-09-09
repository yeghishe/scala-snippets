package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 6/3/15.
 */
object CobindSnippets extends App {
  import scalaz.std.option._
  import scalaz.syntax.std.option._
  import scalaz.syntax.cobind._

  def isEmpty[A](o: Option[A]) = o.isEmpty

  display(1.some.cobind(isEmpty), """ 1.some.cobind(isEmpty) """)
  display(1.some.coflatMap(isEmpty), """ 1.some.coflatMap(isEmpty) """)

  display(1.some.cojoin, """ 1.some.cojoin """)
  display(1.some.coflatten, """ 1.some.coflatten """)
}
