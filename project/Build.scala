import com.typesafe.sbt.SbtNativePackager
import com.typesafe.sbt.packager.docker.DockerPlugin
import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile
import sbt.Keys._
import sbt._

object Build {
  lazy val allSettings = metadata ++ compilerOptions ++ Seq(
    libraryDependencies ++= Dependencies.core
  )

  lazy val metadata = Seq(
    organization := "pl.alkhalili",
    version := "0.1",
    scalaVersion := "2.13.4"
  )

  lazy val compilerOptions = Seq(
    scalacOptions in Compile ++= Seq(
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-deprecation",
      "-Xfatal-warnings",
      "-Ywarn-value-discard",
      "-Xlint:missing-interpolator"
    ),
    scalafmtOnCompile := true
  )
}

object Dependencies {
  import Versions._

  lazy val Http4s = Seq(
    "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % Http4sVersion,
    "org.http4s" %% "http4s-circe"        % Http4sVersion,
    "org.http4s" %% "http4s-dsl"          % Http4sVersion
  )

  lazy val BCrypt = Seq(
    "com.github.t3hnar" %% "scala-bcrypt" % BCryptVersion
  )

  lazy val Cats = Seq(
    "org.typelevel" %% "cats-core" % CatsVersion
  )

  lazy val CatsEffect = Seq(
    "org.typelevel" %% "cats-effect" % CatsEffectVersion
  )

  lazy val Circe = Seq(
    "io.circe" %% "circe-generic" % CirceVersion
  )

  lazy val Database = Seq(
    "org.tpolecat"   %% "doobie-core"   % DoobieVersion,
    "org.tpolecat"   %% "doobie-h2"     % DoobieVersion,
    "org.tpolecat"   %% "doobie-hikari" % DoobieVersion,
    "com.h2database" % "h2"             % H2Version,
    "org.flywaydb"   % "flyway-core"    % FlywayVersion
  )

  lazy val Pureconfig = Seq(
    "com.github.pureconfig" %% "pureconfig"             % PureconfigVersion,
    "com.github.pureconfig" %% "pureconfig-cats-effect" % PureconfigVersion
  )

  lazy val Logback = Seq(
    "ch.qos.logback" % "logback-classic" % LogbackVersion
  )

  /**
    * Returns core dependencies
    */
  val core = Http4s ++ Cats ++ CatsEffect ++ Circe ++ Database ++ Logback ++ Pureconfig

  object Versions {
    lazy val BCryptVersion     = "4.1"
    lazy val CatsVersion       = "2.1.1"
    lazy val CatsEffectVersion = "2.1.1"
    lazy val CirceVersion      = "0.13.0"
    lazy val DoobieVersion     = "0.9.0"
    lazy val FlywayVersion     = "6.3.1"
    lazy val H2Version         = "1.4.200"
    lazy val Http4sVersion     = "0.21.11"
    lazy val LogbackVersion    = "1.2.3"
    lazy val PureconfigVersion = "0.14.0"
    lazy val ScalaPBVersion    = "0.10.9"
  }
}
