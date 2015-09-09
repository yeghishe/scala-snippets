package io.github.yeghishe.scalasnippets.scalazsnippets

import scala.language.higherKinds

/**
 * Created by yeghishe on 6/1/15.
 */
object BindSnippets extends App {
  import scalaz.std.option._
  import scalaz.syntax.std.option._
  import scalaz.syntax.bind._

  display(1.some.flatMap(a => (a + 1).some), """ 1.some.flatMap(a => (a + 1).some) """)
  display(1.some >>= (a => (a + 1).some), """ 1.some >>= (a => (a + 1).some) """)
  display(1.some ∗ (a => (a + 1).some), """ 1.some ∗ (a => (a + 1).some) """)
  display(none[Int] ∗ (a => (a + 1).some), """ none[Int] ∗ (a => (a + 1).some) """)

  display(1.some >> 5.some, """ 1.some >> 5.some """)
  display(none[Int] >> 5.some, """ none[Int] >> 5.some """)

  display(true.some.ifM(1.some, 2.some), """ true.some.ifM(1.some, 2.some) """)
  display(false.some.ifM(1.some, 2.some), """ false.some.ifM(1.some, 2.some) """)
}
