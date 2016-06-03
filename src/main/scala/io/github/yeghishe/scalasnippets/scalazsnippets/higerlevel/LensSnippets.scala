package io.github.yeghishe.scalasnippets.scalazsnippets.higerlevel

import io.github.yeghishe.scalasnippets.scalazsnippets._

/**
 * Created by yeghishe on 11/18/15.
 */
object LensSnippets extends App {
  import scalaz.Lens

  case class Foo(name: String, factor: Int)
  case class FooNode(value: Foo, children: Seq[FooNode] = Seq())

  val second = Lens.lensu[FooNode, FooNode](
    (node, c2) ⇒ node.copy(children = node.children.updated(1, c2)),
    _.children(1)
  )
  val value = Lens.lensu[FooNode, Foo]((node, value) ⇒ node.copy(value = value), _.value)
  val factor = Lens.lensu[Foo, Int]((node, factor) ⇒ node.copy(factor = factor), _.factor)

  val secondFactor = second >=> value >=> factor

  val tree = FooNode(
    Foo("root", 11),
    Seq(
      FooNode(Foo("child1", 1)),
      FooNode(Foo("child2", 2))
    )
  )

  display(secondFactor.get(tree), "(second >=> value >=> factor).get(tree)")
  display(secondFactor.set(tree, 0), "(second >=> value >=> factor).set(tree, 0)")
  display(secondFactor.mod(_ * 2, tree), "(second >=> value >=> factor).mod(_ * 2, tree)")


}
