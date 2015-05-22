package io.github.yeghishe.scalasnippets.akkasnippets.simplecache

import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

/**
 * Created by yeghishe on 5/21/15.
 */
trait Config {
  private val config = ConfigFactory.load()
  val simpleCacheConfig = config.getConfig("scalasnippets.akkasnippets.simplecache")
  def askTimeout = Duration(simpleCacheConfig.getDuration("ask-timeout", SECONDS), SECONDS)
  def cacheDuration = Duration(simpleCacheConfig.getDuration("cache-duration", MINUTES), MINUTES)
}
