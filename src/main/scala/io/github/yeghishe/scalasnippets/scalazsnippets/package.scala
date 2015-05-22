package io.github.yeghishe.scalasnippets

/**
 * Created by yeghishe on 5/14/15.
 */
import scalaz.std.string._
import scalaz.syntax.equal._
import scalaz.syntax.monoid._

package object scalazsnippets {
  def display(s: Any, description: String = ∅): Unit = {
    val length = 60
    println(
      if (description ≟ ∅) s"${" " * (length / 2)}$s"
      else s"$description${" " * (length - description.length)}$s"
    )
  }
}
