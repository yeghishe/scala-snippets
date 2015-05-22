package io.github.yeghishe.scalasnippets.scalazsnippets

import scalaz.Scalaz._
import scalaz._

object MaybeSnippets extends App {
  Seq(
    Maybe.empty[Int],
    1.just,
    Maybe.empty[Int] \/> "Value not set",
    1.just \/> "Value not set"
  ).foreach(println)
}
