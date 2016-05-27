name := """store"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  "com.amazonaws" % "aws-java-sdk" % "1.10.69",
  javaWs
)

includeFilter in (Assets, LessKeys.less) := "*.less"

excludeFilter in (Assets, LessKeys.less) := "_*.less"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

