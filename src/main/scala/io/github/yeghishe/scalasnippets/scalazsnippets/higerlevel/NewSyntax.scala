package io.github.yeghishe.scalasnippets.scalazsnippets.higerlevel

import io.github.yeghishe.scalasnippets.scalazsnippets._

/**
 * Created by yeghishe on 11/18/15.
 */
object NewSyntax extends App {
  import scalaz.syntax.id._

  def incr(i: Int): Int = i + 1
  def str(i: Int): String = i.toString

  display(str(incr(1)), "str(incr(1))")
  display(1 |> incr |> str, "str(incr(1))")
  display(1 ▹ incr ▹ str, "str(incr(1))")
}
