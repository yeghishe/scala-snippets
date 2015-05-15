package io.github.yeghishe

/**
 * Created by yeghishe on 5/14/15.
 */
import scalaz.std.string._
import scalaz.syntax.equal._
import scalaz.syntax.monoid._

package object scalazsnippets {
  def display(s: Any, description: String = ∅): Unit = {
    val lenght = 50
    println(
      if (description ≟ ∅) s"${" " * (lenght / 2)}$s"
      else s"$description${" " * (lenght - description.length)}$s"
    )
  }
}
