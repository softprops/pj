//addSbtPlugin("net.databinder" % "conscript-plugin" % "0.3.4")

//addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.1")

addSbtPlugin("com.jsuereth" % "xsbt-gpg-plugin" % "0.6")

resolvers ++= Seq(
  "less is" at "http://repo.lessis.me",
  "coda" at "http://repo.codahale.com"
)
