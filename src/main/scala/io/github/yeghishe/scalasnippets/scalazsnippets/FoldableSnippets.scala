package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 5/15/15.
 */
object FoldableSnippets extends App {
  import scalaz.std.anyVal.doubleInstance
  import scalaz.std.anyVal.intInstance
  import scalaz.std.string._
  import scalaz.std.option._
  import scalaz.std.list._
  import scalaz.syntax.equal._
  import scalaz.syntax.foldable._

  display(List(1, 2, 3).foldMap(), """ List(1, 2, 3).foldMap() """)
  display(List(1, 2, 3).foldMap((a: Int) ⇒ a.toString), """ List(1, 2, 3).foldMap((a: Int) => a.toString) """)
  display(nil[Int].foldMap(), """ nil[Int].foldMap() """)

  display(List(1, 2, 3).foldMap1Opt(), """ List(1, 2, 3).foldMap1Opt() """)
  display(nil[Int].foldMap1Opt(), """ nil[Int].foldMap1Opt() """)

  display(List(1, 2, 3, 4).foldRight(1)(_ * _), """ List(1, 2, 3, 4).foldRight(1)(_ * _) """)

  display(List(1, 2, 3).foldMapRight1Opt(a ⇒ a + 1)(_ + _), """ List(1, 2, 3).foldMapRight1Opt(a => a + 1)(_ + _) """)
  display(nil[Int].foldMapRight1Opt(a ⇒ a + 1)(_ + _), """ nil[Int].foldMapRight1Opt(a => a + 1)(_ + _) """)

  display(List(1, 2, 3).foldRight1Opt(_ + _), """ List(1, 2, 3).foldRight1Opt(_ + _) """)
  display(nil[Int].foldRight1Opt(_ + _), """ nil[Int].foldRight1Opt(_ + _) """)

  display(List(1, 2, 3).foldRight(0)(_ - _), """ List(1, 2, 3, 4).foldRight(1)(_ * _) """)
  display(List(1, 2, 3).foldLeft(0)(_ - _), """ List(1, 2, 3, 4).foldRight(1)(_ * _) """)

  display(List(1, 2, 3).foldRightM(0)((a, b) ⇒ Option(a + b)), """ List(1, 2, 3).foldRightM(0)((a, b) ⇒ Option(a + b)) """)
  display(List(1, 2, 3).foldMapM(a ⇒ Option(a.toString)), """ List(1, 2, 3).foldRightM(0)((a, b) ⇒ Option(a + b)) """)
  display(List(1, 2, 3).concatenate, """ List(1, 2, 3).concatenate """)

  display(List(1, 2, 3).length, """ List(1, 2, 3).length """)
  display(List(1, 2, 3).index(0), """ List(1, 2, 3).index(0) """)
  display(List(1, 2, 3).index(3), """ List(1, 2, 3).index(3) """)
  display(List(1, 2, 3).indexOr(-1, 0), """ List(1, 2, 3).indexOr(-1, 0) """)
  display(List(1, 2, 3).indexOr(-1, 3), """ List(1, 2, 3).indexOr(-1, 3) """)
  display(List(1, 2, 3).sumr, """ List(1, 2, 3).sumr """)
  display(List(1, 2, 3).suml, """ List(1, 2, 3).suml """)

  display(List(1, 1, 1) ∀ (a ⇒ (a % 2) ≠ 0), """ List(1, 1, 1) ∀ (a ⇒ (a % 2) ≠ 0) """)
  display(List(1, 2, 3) ∃ (a ⇒ (a % 2) ≟ 0), """ List(1, 2, 3) ∃ (a ⇒ (a % 2) ≟ 0) """)

  display(List(1, 2, 3).maximum, """ List(1, 2, 3).maximum """)
  display(List(0D, math.Pi).maximumOf(math.cos), """ List(0D, math.Pi).maximumOf(math.cos) """)
  display(List(0D, math.Pi).maximumBy(math.cos), """ List(0D, math.Pi).maximumBy(math.cos) """)

  display(List(1, 2, 3).minimum, """ List(1, 2, 3).minimum """)
  display(List(0D, math.Pi).minimumOf(math.cos), """ List(0D, math.Pi).minimumOf(math.cos) """)
  display(List(0D, math.Pi).minimumBy(math.cos), """ List(0D, math.Pi).minimumBy(math.cos) """)

  display(List(1, 2, 3).empty, """ List(1, 2, 3).empty """)
  display(nil[Int].empty, """ nil[Int].empty """)

  display(List(1, 2, 3).element(1), """ List(1, 2, 3).element(1) """)
  display(List(1, 2, 3).element(4), """ List(1, 2, 3).element(4) """)

  display(List(2, 4, 1, 3, 5).splitWith(a ⇒ (a % 2) ≟ 0), """ List(1, 2, 3).element(4) """)
  display(List(1, 2, 3).selectSplit(a ⇒ (a % 2) ≟ 0), """ List(1, 2, 3).selectSplit(a ⇒ (a % 2) ≟ 0) """)
  display(List(1, 2, 3).intercalate(10), """ List(1, 2, 3).intercalate(10) """)
}
