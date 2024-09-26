ThisBuild / version := "0.1.0-SNAPSHOT"

Configuration.settings

libraryDependencies ++= Dependencies.production
libraryDependencies ++= Dependencies.test

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "api_base"
  )
