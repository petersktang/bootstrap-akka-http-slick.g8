name := "bootstrap-akka-http"
organization := "de.innfactory"
version := "1.0.0"
scalaVersion := Version.Scala

libraryDependencies ++= {
  Seq(
    Library.swagger,
    Library.swaggerAkka,
    Library.akkaActor,
    Library.akkaHttp,
    Library.akkaHttpCors,
    Library.akkaHttpCirce,
    Library.akkaStream,
    Library.akkaJwt,
    Library.log4jCore,
    Library.slf4jLog4jBridge,
    Library.akkaLog4j,
    Library.slick,
    Library.slickHikaricp,
    Library.postgresql,
    "com.h2database" % "h2" % "1.4.196",
    Library.slickRepo,
    Library.flywaydb,
    Library.circeCore,
    Library.circeGeneric,
    Library.circeParser,

    "com.google.inject" % "guice" % "4.1.0",
    // "com.google.inject.extensions" % "guice-servlet" % "4.1.0",
    "net.codingwell" %% "scala-guice" % "4.1.0",
    "org.apache.shiro" % "shiro-guice" % "1.4.0",
    "com.stormpath.shiro" % "stormpath-shiro-core" % "0.7.2",

    TestLibrary.akkaTestkit,
    TestLibrary.akkaHttpTestkit,
    TestLibrary.postgresqlEmbedded,
    TestLibrary.scalaTest
  )
}

Revolver.settings
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerExposedPorts := Seq(8080)
dockerEntrypoint := Seq("bin/%s" format executableScriptName.value, "-Dconfig.resource=docker.conf")
