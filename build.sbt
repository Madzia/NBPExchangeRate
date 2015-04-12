name := """NBP"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.squeryl" % "squeryl_2.11" % "0.9.5-7",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  jdbc,
  anorm,
  cache,
  ws
)