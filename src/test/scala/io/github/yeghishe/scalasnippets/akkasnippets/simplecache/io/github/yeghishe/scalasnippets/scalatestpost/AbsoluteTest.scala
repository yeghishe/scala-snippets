package io.github.yeghishe.scalasnippets.akkasnippets.simplecache.io.github.yeghishe.scalasnippets.scalatestpost

import org.scalatest.{Matchers, PropSpec}
import org.scalatest.prop.TableDrivenPropertyChecks

/**
 * Created by yeghishe on 5/30/15.
 */
class AbsoluteTest extends PropSpec with TableDrivenPropertyChecks with Matchers {
  val examples = Table(
    ("input", "result"),
    (0, 0),
    (1, 1),
    (-1, 1))

  property("math.abs should return the absolute value") {
    forAll(examples) { (input, result) =>
      math.abs(input) should be(result)
    }
  }
}
