package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 5/15/15.
 */
object ApplicativeSnippets extends App {
  import scalaz.std.option.optionInstance
  import scalaz.syntax.std.option._
  import scalaz.syntax.applicative._

  display(1.η, """ 1.η """)
  display(1.point, """ 1.point """)
  display(1.pure, """ 1.pure """)

  /* has issues, returns F[Unit] not what documentation says
  display(1.some.unlessM(true), """ 1.some.unlessM(true) """)
  display(1.some.unlessM(false), """ 1.some.unlessM(false) """)

  display(1.some.whenM(true), """ 1.some.whenM(true) """)
  display(1.some.whenM(false), """ 1.some.whenM(false) """)
  */

  display(1.some.replicateM(3), """ 1.some.replicateM(3) """)
  display(1.some.replicateM_(3), """ 1.some.replicateM_(3) """)
}
