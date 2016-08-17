name := "InFriends-core"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.11.0-M2",
  "com.github.finagle" %% "finch-circe" % "0.11.0-M2",
  "com.github.finagle" %% "finch-test" % "0.11.0-M2"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"

enablePlugins(JavaAppPackaging)

mainClass in Universal := Some("com.nico.infriends.core.app")

