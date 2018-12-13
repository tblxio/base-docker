name := "base_docker_test"

organization := "io.techhublisbon"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.whisk" %% "docker-testkit-scalatest" % "0.9.8" % "test",
  "com.whisk" %% "docker-testkit-impl-spotify" % "0.9.8" % "test"
)

scalafmtOnCompile in ThisBuild := true
scalafmtTestOnCompile in ThisBuild := true
scalafmtFailTest in ThisBuild := true
