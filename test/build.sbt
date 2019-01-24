name := "base_docker_test"

organization := "io.techhublisbon"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.whisk" %% "docker-testkit-scalatest" % "0.9.8" % "test",
  "com.whisk" %% "docker-testkit-impl-spotify" % "0.9.8" % "test",
  "org.json4s" %% "json4s-native" % "3.5.1" % "test",
  "org.json4s" %% "json4s-jackson" % "3.5.1" % "test"
)

scalafmtOnCompile in ThisBuild := true
scalafmtTestOnCompile in ThisBuild := true
scalafmtFailTest in ThisBuild := true
