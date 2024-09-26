import sbt.ModuleID
import sbt._

object Dependencies {
  object Versions {
    val akka = "2.7.0"
    val akkaHttp = "10.2.10"
  }

  val production: Seq[ModuleID] = Seq(
    "com.github.nscala-time" %% "nscala-time" % "2.24.0",
    "com.lihaoyi" %% "pprint" % "0.5.9",
    "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp,
    "com.typesafe.akka" %% "akka-actor" % Versions.akka,
    "com.typesafe.akka" %% "akka-stream" % Versions.akka, // Explicit dependency due to: https://bit.ly/akka-http-25
    "com.typesafe.akka" %% "akka-http-spray-json" % Versions.akkaHttp,
    "org.tpolecat" %% "doobie-core" % "1.0.0-RC1",
    "org.tpolecat" %% "doobie-hikari" % "1.0.0-RC1",
    "org.tpolecat" %% "doobie-quill" % "1.0.0-RC1",
    "mysql" % "mysql-connector-java" % "8.0.33", // conector MySQL
    "de.heikoseeberger" %% "akka-http-circe" % "1.39.2",
    "io.circe" %% "circe-generic" % "0.14.5",  // Para derivar automáticamente los codecs
    "io.circe" %% "circe-parser" % "0.14.5",
  //    Cierro
//    "com.github.scopt" %% "scopt" % "3.7.0", // Command Line Commands such as de DbTablesCreator
//    "com.newmotion" %% "akka-rabbitmq" % "5.0.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3", // Logging backend implementation
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4", // SLF4J Scala wrapper
    "net.logstash.logback" % "logstash-logback-encoder" % "4.11", // Log JSON encoder
    //    Agregados de prueba
    "com.typesafe" % "config" % "1.4.1"
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.2.16" % Test,
    "org.scalamock" %% "scalamock" % "5.2.0" % Test,
    "com.typesafe.akka" %% "akka-testkit" % Versions.akka % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % Versions.akkaHttp % Test
  )
}
