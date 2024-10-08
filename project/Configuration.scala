import sbt.Keys.*
import sbt.{Def, Tests, *}

object Configuration {

  val settings: Seq[Def.Setting[_ >: Task[Option[String]] with Task[
    Seq[String]
  ] with String with File with Boolean with Task[Seq[TestOption]]]] =
    Seq(
      name := "api_base",
      organization := "bqn",
      scalaVersion := "2.13.8",
      mainClass := Some("api.entry_point.ScalaHttpApi"),
      // Custom folders path (/src/main/scala and /src/test/scala by default)
      Compile / mainClass := Some("api.entry_point.ScalaHttpApi"),
      Compile / scalaSource := baseDirectory.value / "/src/main",
      Test / scalaSource := baseDirectory.value / "/src/test",
      Compile / resourceDirectory := baseDirectory.value / "conf",
      javaOptions += "-Duser.timezone=UTC",
      scalacOptions ++= Seq(
        "-deprecation",                                                        // Emit warning and location for usages of deprecated APIs.
        "-encoding",
        "utf-8",                                                               // Specify character encoding used by source files.
        "-explaintypes",                                                       // Explain type errors in more detail.
        "-feature",                                                            // Emit warning and location for usages of features that should be imported explicitly.
        "-language:existentials",                                              // Existential types (besides wildcard types) can be written and inferred
        "-language:experimental.macros",                                       // Allow macro definition (besides implementation and application)
        "-language:higherKinds",                                               // Allow higher-kinded types
        "-language:implicitConversions",                                       // Allow definition of implicit functions called views
        "-unchecked",                                                          // Enable additional warnings where generated code depends on assumptions.
        "-Xcheckinit",                                                         // Wrap field accessors to throw an exception on uninitialized access.
        //        "-Xfatal-warnings", // Fail the compilation if there are any warnings.
        "-Xfuture",                                                            // Turn on future language features.
        "-Xlint"                                                               // Habilita advertencias útiles por defecto
        //        "-Xfatal-warnings",        // Trata las advertencias como errores (opcional)
        //        "-Xlint:adapted-args", // Warn if an argument list is modified to match the receiver.
        //        "-Xlint:constant", // Evaluation of a constant arithmetic expression results in an error.
        //        "-Xlint:delayedinit-select", // Selecting member of DelayedInit.
        //        "-Xlint:doc-detached", // A Scaladoc comment appears to be detached from its element.
        //        "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
        //        "-Xlint:infer-any", // Warn when a type argument is inferred to be `Any`.
        //        "-Xlint:missing-interpolator", // A string literal appears to be missing an interpolator id.
        //        "-Xlint:nullary-unit", // Warn when nullary methods return Unit.
        //        "-Xlint:option-implicit", // Option.apply used implicit view.
        //        "-Xlint:package-object-classes", // Class or object defined in package object.
        //        "-Xlint:poly-implicit-overload", // Parameterized overloaded implicit methods are not visible as view bounds.
        //        "-Xlint:private-shadow", // A private field (or class parameter) shadows a superclass field.
        //        "-Xlint:stars-align", // Pattern sequence wildcard must align with sequence component.
        //        "-Xlint:type-parameter-shadow", // A local type parameter shadows a type already in scope
        //        "-Ywarn-dead-code", // Warn when dead code is identified.
        //        "-Ywarn-extra-implicit", // Warn when more than one implicit parameter section is defined.
        //        "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
        //        "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`.
        //        "-Ywarn-nullary-override", // Warn when non-nullary `def f()' overrides nullary `def f'.
        //        "-Ywarn-nullary-unit", // Warn when nullary methods return Unit.
        //        "-Ywarn-numeric-widen", // Warn when numerics are widened.
        //        "-Ywarn-unused:implicits", // Warn if an implicit parameter is unused.
        //        "-Ywarn-unused:imports", // Warn if an import selector is not referenced.
        //        "-Ywarn-unused:locals", // Warn if a local definition is unused.
        //        "-Ywarn-unused:params", // Warn if a value parameter is unused.
        //        "-Ywarn-unused:patvars", // Warn if a variable bound in a pattern is unused.
        //        "-Ywarn-unused:privates", // Warn if a private member is unused.
        //        "-Ywarn-value-discard" // Warn when non-Unit expression results are unused.
      ),
      //      Compile / console / scalacOptions --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings"), // Leave the console REPL usable :P
      //      Compile / run / scalacOptions -= "-Xcheckinit",             // Expensive to run in prod
      //      Test / compile / scalacOptions --= Seq("-Xfatal-warnings"), // Due to deprecated ETA expansion used with ScalaMock
      // Test options
      Test / parallelExecution := false,
      Test / testForkedParallel := false,
      Test / fork := false,
      Test / testOptions ++= Seq(
        Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"), // Save test reports
        Tests.Argument("-oDF")                                                 // Show full stack traces and time spent in each test
      )
    )
}
