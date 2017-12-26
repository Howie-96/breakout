enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.4",
  "com.lihaoyi" %%% "scalatags" % "0.6.7"
)
scalaVersion in ThisBuild := "2.12.4"
