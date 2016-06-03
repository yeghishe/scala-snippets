package io.github.yeghishe.scalasnippets.scalazsnippets.higerlevel

import io.github.yeghishe.scalasnippets.scalazsnippets._

/**
 * Created by yeghishe on 11/18/15.
 */
object MemorizationSnippets extends App {
  import scalaz.Memo

  def expensive(i: Int): Int = i + 1
  val memo = Memo.immutableHashMapMemo((i: Int) ⇒ expensive(i))

  display(memo(1), "memo(1)")
  display(memo(1), "memo(1)")
}
