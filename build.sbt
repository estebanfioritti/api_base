import sbt.Compile

ThisBuild / version := "0.1.0-SNAPSHOT"

Configuration.settings

libraryDependencies ++= Dependencies.production
libraryDependencies ++= Dependencies.test

ThisBuild / scalaVersion := "2.13.8"
// Configuración del plugin assembly
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true)

//assemblyMergeStrategy in assembly := {
//  case PathList("META-INF", xs@_*) => MergeStrategy.discard
//  case _ => MergeStrategy.first
//}

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "services", xs @ _*) => MergeStrategy.concat
  case "application.conf" | "reference.conf" => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

lazy val root = (project in file("."))
  .settings(
    name := "api_base",
    version := "1.0",
    scalaVersion := "2.13.12",
    Compile / packageBin := (Compile / assembly).value
  )