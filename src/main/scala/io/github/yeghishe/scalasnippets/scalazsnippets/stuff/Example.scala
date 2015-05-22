package io.github.yeghishe.scalasnippets.scalazsnippets.stuff

import scalaz.Scalaz._
import scalaz._
/*
import scalaz.syntax.std.option._
import scalaz.State
import scalaz.Monoid
import scalaz.syntax.traverse._
import scalaz.std.list._
*/

/**
 * Created by yeghishe on 2/11/15.
 */
object Example {
  type StateCache[A] = State[Cache, A]

  case class Timestamped[A](value: A, timestamp: Long)

  case class FollowerStats(
    username: String,
    numFollowers: Int,
    numFollowing: Int)

  case class Cache(
      stats: Map[String, Timestamped[FollowerStats]],
      hits: Int,
      misses: Int) {
    def get(username: String): Option[Timestamped[FollowerStats]] =
      stats.get(username)

    def update(username: String, followerStats: Timestamped[FollowerStats]): Cache =
      copy(stats = stats + (username -> followerStats))

    def recordHit: Cache = copy(hits = hits + 1)
    def recordMiss: Cache = copy(misses = misses + 1)
  }

  implicit object CacheMonoid extends Monoid[Cache] {
    override def zero: Cache =
      Cache(Map.empty, 0, 0)

    override def append(f1: Cache, f2: => Cache): Cache =
      Cache(f1.stats ++ f2.stats, f1.hits + f2.hits, f1.misses + f2.misses)
  }

  trait SocialService {
    def followerStats(username: String): StateCache[FollowerStats]
  }

  object SocialService {
    implicit object SocialServiceImpl extends SocialService {
      override def followerStats(username: String): StateCache[FollowerStats] = for {
        ofs <- checkCache(username)
        fs <- ofs.fold(receive(username))(State.state)
      } yield fs

      private def checkCache(username: String): StateCache[Option[FollowerStats]] = for {
        ofs <- State.gets { s: Cache =>
          s.get(username).collect { case Timestamped(fs, ts) if !stale(ts) => fs }
        }
        _ <- State.modify[Cache] { s => ofs ? s.recordHit | s.recordMiss }
      } yield ofs

      private def stale(ts: Long): Boolean = System.currentTimeMillis() - ts > (5 * 60 * 1000L)

      private def receive(username: String): StateCache[FollowerStats] = for {
        fs <- State.state(callWebService(username))
        tfs = Timestamped(fs, System.currentTimeMillis())
        _ <- State.modify[Cache](s => s.update(username, tfs))
      } yield fs

      private def callWebService(username: String): FollowerStats = FollowerStats(username, 0, 0)
    }
  }
}

import io.github.yeghishe.scalasnippets.scalazsnippets.stuff.Example._

object DemoExample extends App {
  val socialService = implicitly[SocialService]

  val listOfState = List(socialService.followerStats("a"), socialService.followerStats("b"), socialService.followerStats("a"))
  val stateOfList = listOfState.sequence[StateCache, FollowerStats]

  //println(State.state[StateCache](10).runZero)
  println(10.point[StateCache].runZero)

  println(stateOfList.runZero)
}
