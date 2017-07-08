import de.johoop.testngplugin.TestNGPlugin._

name := "example-mininal"

version := "1.1-SNAPSHOT"

scalaVersion := "2.12.2"

lazy val akkaVersion = "2.5.3"

libraryDependencies ++= Seq(
  "io.ebean" % "ebean-agent" % "10.3.1",
  "io.ebean" % "ebean" % "10.4.1",
  "io.ebean" % "ebean-querybean" % "10.1.1",
  "io.ebean" % "querybean-generator" % "10.1.1",
  "org.slf4j" % "slf4j-api" % "1.7.20",
  "org.avaje.composite" % "composite-testing" % "3.1" % Test
)

testNGSettings

