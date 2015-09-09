package io.github.yeghishe.scalasnippets.scalazsnippets

import scalaz.Apply

/**
 * Created by yeghishe on 5/15/15.
 */
object ApplySnippets extends App {
  // This will bring an implicit list instance which extends MonadPlus which in it's turn implements Functor.
  import scalaz.std.option._
  import scalaz.std.list._
  import scalaz.syntax.std.option._
  import scalaz.syntax.apply._

  display(1.some <*> ((_: Int) + 1).some, """ 1.some <*> ((_:Int) + 1).some """)
  display(none <*> ((_: Int) + 1).some, """ none <*> ((_:Int) + 1).some """)
  display(1.some <*> none, """ 1.some <*> none """)

  display(1.some.tuple(2.some), """ 1.some.tuple(2.some) """)
  display(none.tuple(2.some), """ none.tuple(2.some) """)
  display(1.some.tuple(none), """ 1.some.tuple(none) """)

  display(List(1, 2).tuple(List("a", "b")), """ List(1,2).tuple(List("a", "b")) """)

  display(1.some *> 2.some, """ 1.some *> 2.some """)
  display(none *> 2.some, """ none *> 2.some """)
  display(1.some *> none, """ 1.some *> none """)

  display(1.some <* 2.some, """ 1.some <* 2.some """)
  display(none <* 2.some, """ none <* 2.some """)
  display(1.some <* none, """ 1.some <* none """)

  display(("a".some ⊛ "b".some)(_ + _), """ ("a".some ⊛ "b".some)(_ + _) """)
  display(^("a".some, "b".some)(_ + _), """ ^("a".some, "b".some)(_ + _) """)
  display(^^("a".some, "b".some, "c".some)(_ + _ + _), """ ^^("a".some, "b".some, "c".some)(_ + _ + _) """)
}
