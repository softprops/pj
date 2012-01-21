object Build extends sbt.Build {
  import sbt._
  lazy val root = Project("root", file(".")) aggregate(pj, app)
  lazy val pj = Project("pj", file("pj"))
  lazy val app = Project("app", file("app"))
}
