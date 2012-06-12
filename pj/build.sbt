name := "pj"

scalacOptions += "-deprecation"

crossScalaVersions := Seq("2.9.1", "2.9.1-1", "2.9.2")

libraryDependencies += "org.codehaus.jackson" % "jackson-core-asl" % "1.9.0"

seq(lsSettings: _*)

(LsKeys.tags in LsKeys.lsync) := Seq("json")

description := "A pretty printer for json"
