name := "InFriends-core"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.11.0-M2",
  "com.github.finagle" %% "finch-argonaut" % "0.11.0-M2",
  "com.github.finagle" %% "finch-test" % "0.11.0-M2"
)

enablePlugins(JavaAppPackaging)

mainClass in Universal := Some("com.nico.infriends.core.app")