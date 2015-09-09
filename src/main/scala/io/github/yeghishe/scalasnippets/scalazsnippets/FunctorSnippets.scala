package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 5/15/15.
 */
object FunctorSnippets extends App {
  // This will bring an implicit list instance which extends MonadPlus which in it's turn implements Functor.
  import scalaz.std.list._
  import scalaz.syntax.functor._

  display(List(1, 2, 3) ∘ (_ + 1), """ List(1,2,3) ∘ (_ + 1) """)

  display(List(1, 2, 3).strengthL("a"), """ List(1,2,3).strengthL("a") """)
  display(List(1, 2, 3).strengthR("a"), """ List(1,2,3).strengthR("a") """)
  display(List(1, 2, 3).fpair, """ List(1, 2, 3).fpair """)
  display(List(1, 2, 3).fproduct(_ + 1), """ List(1, 2, 3).fproduct(_ + 1) """)
  display(List(1, 2, 3).void, """ List(1, 2, 3).void """)
  display(
    List(1, 2, 3).fpoint(scalaz.std.option.optionInstance),
    """ List(1, 2, 3).fpoint(scalaz.std.option.optionInstance) """)
  display(List(1, 2, 3) >| "a", """ List(1, 2, 3) >| "a" """)
}
