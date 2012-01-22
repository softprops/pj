organization := "me.lessis"

name := "pj"

version := "0.1.0"

scalacOptions += "-deprecation"

libraryDependencies += "org.codehaus.jackson" % "jackson-core-asl" % "1.9.0"

seq(lsSettings: _*)

(LsKeys.tags in LsKeys.lsync) := Seq("json")

(description in LsKeys.lsync) :=
  "A pretty printer for json"

licenses in LsKeys.lsync := Seq(
  ("MIT", url("https://github.com/softprops/pj/blob/master/LICENSE"))
)

homepage in LsKeys.lsync := Some(url("https://github.com/softprops/pj#readme"))

publishTo := Some("Scala Tools Nexus" at "http://nexus.scala-tools.org/content/repositories/releases/")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

