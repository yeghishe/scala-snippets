import scalariform.formatter.preferences._

organization := "io.yeghishe"
name := "scala-snippets"
version := "0.0.1"
scalaVersion := "2.11.6"
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= {
  val scalazV = "7.1.2"
  val scalazStreamV = "0.8"
  val akkaV = "2.3.9"
  Seq(
    "org.scalaz"        %% "scalaz-core"                 % scalazV,
    "org.scalaz"        %% "scalaz-effect"               % scalazV,
    "org.scalaz"        %% "scalaz-typelevel"            % scalazV,
    "org.scalaz.stream" %% "scalaz-stream"               % scalazStreamV,
    "com.typesafe.akka" %% "akka-actor"                  % akkaV,
    "org.scalaz"        %% "scalaz-scalacheck-binding"  % scalazV   % "it,test",
    "com.typesafe.akka" %% "akka-testkit"                % akkaV     % "it,test",
    "org.scalatest"     %% "scalatest"                   % "2.2.4"   % "it,test",
    "org.scalamock"     %% "scalamock-scalatest-support" % "3.2"     % "it,test"
  )
}

lazy val root = project.in(file(".")).configs(IntegrationTest)
Defaults.itSettings
scalariformSettings
Revolver.settings
enablePlugins(JavaAppPackaging)

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

initialCommands := """|import scalaz._
                      |import scalaz._
                      |import akka.actor._
                      |import akka.pattern._
                      |import akka.util._
                      |import scala.concurrent._
                      |import scala.concurrent.duration._""".stripMargin

