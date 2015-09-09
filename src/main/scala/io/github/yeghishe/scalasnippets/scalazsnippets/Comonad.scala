package io.github.yeghishe.scalasnippets.scalazsnippets

import scalaz.NonEmptyList

/**
 * Created by yeghishe on 6/3/15.
 */
object Comonad extends App {
  import scalaz.NonEmptyList._
  import scalaz.syntax.comonad._

  display(NonEmptyList(1, 2, 3).copoint, """ NonEmptyList(1, 2, 3).copoint """)
}
