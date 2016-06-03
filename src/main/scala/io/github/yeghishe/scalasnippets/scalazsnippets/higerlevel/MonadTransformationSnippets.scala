package io.github.yeghishe.scalasnippets.scalazsnippets.higerlevel

import scala.concurrent.{Await, Future}
import scala.language.{higherKinds, postfixOps}
import scalaz.{Functor, Monad}

/**
  * Created by yeghishe on 11/20/15.
  */
object MonadTransformationSnippets extends App {
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration._
  import scalaz.std.option._
  import scalaz.std.scalaFuture._
  import scalaz.std.list._
  import scalaz.syntax.std.option._
  import scalaz.syntax.monad._

  /*
  // Iteration 1
  case class FutureOption[A](run: Future[Option[A]])
  implicit val futureOptionMonad = new Monad[FutureOption] {
    override def point[A](a: => A): FutureOption[A] = FutureOption(a.point[Option].point[Future])
    override def bind[A, B](fa: FutureOption[A])(f: (A) => FutureOption[B]): FutureOption[B] = FutureOption(
      fa.run.flatMap(_.fold(none[B].point[Future])(f(_).run)))
  }
  val f1 = FutureOption(Future(1.some))
  val f2 = FutureOption(Future(2.some))
  val result = for {
    a <- f1
    b <- f2
  } yield a + b

  println(Await.result(result.run, 1 second))
  */

  /*
  // Iteration 2
  case class OptionT[M[_], A](run: M[Option[A]]) {
    def map[B](f: A ⇒ B)(implicit F: Functor[M]): OptionT[M, B] = OptionT(run.map(_.map(f)))
    def flatMap[B](f: A ⇒ OptionT[M, B])(implicit F: Monad[M]): OptionT[M, B] =
      OptionT(run.flatMap(_.map(f(_).run).getOrElse(none[B].point[M])))
  }

  val f1 = OptionT(Future(1.some))
  val f2 = OptionT(Future(2.some))
  val result1 = for {
    a <- f1
    b <- f2
  } yield a + b
  println(Await.result(result1.run, 1 second))

  val l1 = OptionT(List(1.some))
  val l2 = OptionT(List(2.some))
  val result2 = for {
    a <- l1
    b <- l2
  } yield a + b
  println(result2.run)
  */

  import scalaz.OptionT.optionT


  val f1 = optionT(Future(1.some))
  val f2 = optionT(Future(2.some))
  val result1 = for {
    a <- f1
    b <- f2
  } yield a + b
  println(Await.result(result1.run, 1 second))

  val l1 = optionT(List(1.some))
  val l2 = optionT(List(2.some))
  val result2 = for {
    a <- l1
    b <- l2
  } yield a + b
  println(result2.run)

  import scalaz.std.scalaFuture
}
