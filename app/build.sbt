organization := "me.lessis"

name := "pj-app"

version := "0.1.0-SNAPSHOT"

seq(conscript.Harness.conscriptSettings: _*)

publishTo := Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
