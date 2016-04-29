name := """scala-http-example"""

version := "1.0"

scalaVersion := "2.11.8"

val playVersion = "2.5.3"


libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.0",

  "ch.qos.logback" %  "logback-classic" % "1.1.7",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0",

  "com.typesafe.play" %% "play-json" % playVersion,
  "com.typesafe.play" %% "play-ws" % playVersion,

  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.mockito" % "mockito-all" % "1.8.4" % "test"
)

//********************************************************
// test settings
//********************************************************

def integrationFilter(name: String): Boolean = name endsWith "ITSpec"

def unitFilter(name: String): Boolean = {
  (name endsWith "Spec") && !integrationFilter(name)
}

lazy val CustomIntegrationTest = config("it") extend Test

testOptions in Test := Seq(Tests.Filter(unitFilter))
testOptions in CustomIntegrationTest := Seq(Tests.Filter(integrationFilter))

lazy val root = (project in file("."))
  .configs(CustomIntegrationTest)
  .settings(inConfig(CustomIntegrationTest)(Defaults.testTasks): _*)
