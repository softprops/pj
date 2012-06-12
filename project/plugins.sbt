//addSbtPlugin("net.databinder" % "conscript-plugin" % "0.3.4")

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.2")

addSbtPlugin("com.jsuereth" % "xsbt-gpg-plugin" % "0.6")

resolvers ++= Seq(
  "coda" at "http://repo.codahale.com"
)
