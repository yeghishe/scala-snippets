package io.github.yeghishe.scalasnippets.scalazsnippets

/**
 * Created by yeghishe on 5/14/15.
 */
object OperationsBooleanSnippets extends App {
  import scalaz.syntax.std.boolean._

  display("StringOps")
  display(true ∧ true, """ true ∧ true """)
  display(true ∧ false, """ true ∧ false """)
  display(false ∧ true, """ false ∧ true """)
  display(false ∧ false, """ false ∧ false """)

  display(true ∨ true, """ true ∨ true """)
  display(true ∨ false, """ true ∨ false """)
  display(false ∨ true, """ false ∨ true """)
  display(false ∨ false, """ false ∨ false """)

  display(true !|| true, """ true !|| true """)
  display(true !|| false, """ true !|| false """)
  display(false !|| true, """ false !|| true """)
  display(false !|| false, """ false !|| false """)

  display(true !&& true, """ true !&& true """)
  display(true !&& false, """ true !&& false """)
  display(false !&& true, """ false !&& true """)
  display(false !&& false, """ false !&& false """)

  display(true --> true, """ true --> true """)
  display(true --> false, """ true --> false """)
  display(false --> true, """ false --> true """)
  display(false --> false, """ false --> false """)

  display(true <-- true, """ true <-- true """)
  display(true <-- false, """ true <-- false """)
  display(false <-- true, """ false <-- true """)
  display(false <-- false, """ false <-- false """)

  display(true <-- true, """ true <-- true """)
  display(true <-- false, """ true <-- false """)
  display(false <-- true, """ false <-- true """)
  display(false <-- false, """ false <-- false """)

  display(true ⇐ true, """ true ⇐ true """)
  display(true ⇐ false, """ true ⇐ false """)
  display(false ⇐ true, """ false ⇐ true """)
  display(false ⇐ false, """ false ⇐ false """)

  display(true ⇐ true, """ true ⇐ true """)
  display(true ⇐ false, """ true ⇐ false """)
  display(false ⇐ true, """ false ⇐ true """)
  display(false ⇐ false, """ false ⇐ false """)

  display(true ⇏ true, """ true ⇏ true """)
  display(true ⇏ false, """ true ⇏ false """)
  display(false ⇏ true, """ false ⇏ true """)
  display(false ⇏ false, """ false ⇏ false """)

  display(true ⇍ true, """ true ⇍ true """)
  display(true ⇍ false, """ true ⇍ false """)
  display(false ⇍ true, """ false ⇍ true """)
  display(false ⇍ false, """ false ⇍ false """)

  true.when(println("when"))
  false.unless(println("unless"))

  display(true.fold("a", "b"), """ true.fold("a", "b") """)
  display(false.fold("a", "b"), """ false.fold("a", "b" """)

  display(false ? "a" | "b", """ false ? "a" | "b" """)
  display(true ? "a" | "b", """ true ? "a" | "b" """)

  display(false.option(1), """ false.option(1) """)
  display(true.option(1), """ true.option(1) """)

  display(false.lazyOption(1), """ false.lazyOption(1) """)
  display(true.lazyOption(1), """ true.lazyOption(1) """)

  display(false either "a" or "b", """ false either "a" or "b" """)
  display(true either "a" or "b", """ true either "a" or "b" """)

  {
    import scalaz.std.anyVal.intInstance
    display(true ?? 1, """ true ?? 1 """)
    display(false ?? 1, """ false ?? 1 """)

    display(true !? 1, """ true !? 1 """)
    display(false !? 1, """ false !? 1 """)
  }

  {
    display("anyVal.booleanInstance extends Enum[Boolean] with Show[Boolean]")
    import scalaz.std.anyVal.booleanInstance
    import scalaz.syntax.enum._
    display("EnumOps")
    display(true.succ, """ true.succ """)
    display(true -+- 2, """ true -+- 2 """)
    display(true.pred, """ true.pred """)
    display(true --- 2, """ true -+- 2 """)

    import scalaz.syntax.show._
    display("ShowOps")
    display(true.shows, """ true.show """)
    true.println
  }

  display("Boolean isn't Monoid but BooleanDisjunction and BooleanConjunction are")

  {
    import scalaz.Tags.Conjunction
    import scalaz.std.anyVal.booleanConjunctionNewTypeInstance
    import scalaz.syntax.monoid._

    display(Conjunction(true) ⊹ Conjunction(true), """ Conjunction(true) ⊹ Conjunction(true) """)
    display(Conjunction(true) ⊹ Conjunction(false), """ Conjunction(true) ⊹ Conjunction(false) """)
    display(Conjunction(false) ⊹ Conjunction(true), """ Conjunction(false) ⊹ Conjunction(true) """)
    display(Conjunction(false) ⊹ Conjunction(false), """ Conjunction(true) ⊹ Conjunction(false) """)
    display(Conjunction(true) ⊹ ∅, """ ∅[Conjunction] """)
  }

  {
    import scalaz.Tags.Disjunction
    import scalaz.std.anyVal.booleanDisjunctionNewTypeInstance
    import scalaz.syntax.monoid._

    display(Disjunction(true) ⊹ Disjunction(true), """ Disjunction(true) ⊹ Disjunction(true) """)
    display(Disjunction(true) ⊹ Disjunction(false), """ Disjunction(true) ⊹ Disjunction(false) """)
    display(Disjunction(false) ⊹ Disjunction(true), """ Disjunction(false) ⊹ Disjunction(true) """)
    display(Disjunction(false) ⊹ Disjunction(false), """ Disjunction(true) ⊹ Disjunction(false) """)
    display(Disjunction(true) ⊹ ∅, """ ∅[Disjunction] """)
  }
}
