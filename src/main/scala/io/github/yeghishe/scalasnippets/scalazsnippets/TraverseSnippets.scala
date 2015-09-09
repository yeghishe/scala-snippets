package io.github.yeghishe.scalasnippets.scalazsnippets

import scalaz.{ Applicative, Traverse }

/**
 * Created by yeghishe on 5/26/15.
 */
object TraverseSnippets extends App {

  // TODO: do Applicative first
}

class T[X] extends Traverse[X] {
  override def traverseImpl[G[_], A, B](fa: X[A])(f: (A) => G[B])(implicit evidence$1: Applicative[G]): G[X[B]] = ???
}
