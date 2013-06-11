object Build extends sbt.Build {
  import sbt._
  import sbt.Keys._

  def commonSettings: Seq[Project.Setting[_]] = Defaults.defaultSettings ++ Seq(
    publishTo :=
      Some("nexus-releases" at
           "https://oss.sonatype.org/service/local/staging/deploy/maven2"),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    organization := "me.lessis",
    version := "0.1.0",
    licenses <<= (version)(v =>
      Seq("MIT" ->
          url("https://github.com/softprops/pj/blob/%s/LICENSE" format v))),
    homepage := some(url("https://github.com/softprops/pj/#readme")),
    pomExtra := (
      <scm>
        <url>git@github.com:softprops/pj.git</url>
        <connection>scm:git:git@github.com:softprops/pj.git</connection>
      </scm>
      <developers>
        <developer>
          <id>softprops</id>
          <name>Doug Tangren</name>
          <url>https://github.com/softprops</url>
        </developer>
      </developers>)
  )

  lazy val root = Project(
    "root", file("."),
    settings = Defaults.defaultSettings ++ Seq(
      test := { }, // no tests
      publish := { }, // skip publishing for this root project.
      publishLocal := { } // skip publishing locally,
      //ls.Plugin.LsKeys.skipWrite := true // don't track root in ls
    ))
      .aggregate(pj, app)
  lazy val pj = Project("pj", file("pj"), settings = commonSettings)
  lazy val app = Project("app", file("app"), settings = commonSettings) dependsOn(pj)
}
