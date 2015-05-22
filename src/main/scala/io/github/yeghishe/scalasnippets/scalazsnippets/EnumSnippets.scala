package io.github.yeghishe.scalasnippets.scalazsnippets

import scalaz.Scalaz._
import scalaz._

/**
 * Created by yeghishe on 5/14/15.
 */
object EnumSnippets extends App {
  println(s"true.succ         ${true.succ}")
  println(s"true.pred         ${true.pred}")

  println(s"1 |-> 10          ${1 |-> 10}")
  println(s"1 |--> (2, 10)    ${1 |--> (2, 10)}")
  println(s"1 |--> (2, 11)    ${1 |--> (2, 11)}")
}
