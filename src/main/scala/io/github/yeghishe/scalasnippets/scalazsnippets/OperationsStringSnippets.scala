package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 5/14/15.
 */
object OperationsStringSnippets extends App {
  import scalaz.std.string._
  import scalaz.syntax.monoid._
  import scalaz.syntax.order._
  import scalaz.syntax.show._
  import scalaz.syntax.std.string._

  display("stringInstance is Monoid, Show, Order (order is also Equal)")

  display("StringOps")
  display("code".plural(0), """ "code".plural(0) """)

  display("MonoidOps")
  display("a" ⊹ "b", """ "a" ⊹ "b" """)
  display("hello" ⊹ ∅, """ "hello" ⊹ ∅ """)

  display("ShowOps")
  display("abc".shows, """ "abc".show """)
  "abc".println

  display("EqualOps")
  display("a" ≟ "a", """ "a" ≟ "b" """)
  display("a" ≠ "b", """ "a" ≠ "b" """)

  display("OrderOps")
  display("a" lt "b", """ "a" lt "b" """)
  display("b" lte "b", """ "b" lte "b" """)
  display("b" gt "a", """ "b" gt "a" """)
  display("b" gte "b", """ "b" gte "b" """)
  display("a" ?|? "b", """ "a" ?|? "b" """)
}
