organization := "me.lessis"

name := "pj-app"

version := "0.1.0"

resolvers += Classpaths.typesafeResolver

libraryDependencies <+= (sbtVersion)(
  "org.scala-sbt" %
   "launcher-interface" %
    _ % "provided")

description := "A conscript interface for prettifying json strings and streams"
