package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 5/15/15.
 */
object ApplicativeSnippets extends App {
  import scalaz.std.list._
  import scalaz.syntax.applicative._

  display(1.η, """ 1.η """)

  // TODO: do Foldable and Traverse first
  //display(List(1,2,3).unlessM(cond = false), """ 1.η """)
}
