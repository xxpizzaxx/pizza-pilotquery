import AssemblyKeys._

name := "pilotquery"

organization := "moe.pizza"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
    "Sonatype Snapshots"  at "https://oss.sonatype.org/content/repositories/snapshots/",
    "Sonatype Releases"   at "http://oss.sonatype.org/content/repositories/releases",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
    val configVersion     = "1.2.1"
    val logbackVersion    = "1.1.1"
    val scalatraVersion   = "2.3.0"
    val jettyVersion      = "9.3.0.M1"
    val json4sVersion     = "3.2.9"
    val casbahVersion     = "2.8.0"
    val salatVersion      = "1.9.9"
    val embedMongoVersion = "0.2.2"
    val macwireVersion    = "0.8.0"
    val scalaMockVersion  = "3.2.1"
    val jongoVersion      = "1.2"
    Seq(
        "com.typesafe"              %   "config"                      % configVersion,
        "org.scalatra"              %%  "scalatra"                    % scalatraVersion,
        "org.scalatra"              %%  "scalatra-json"               % scalatraVersion,
        "org.scalatra"              %%  "scalatra-scalatest"          % scalatraVersion % "test",
        "org.json4s"                %%  "json4s-jackson"              % json4sVersion,
        "org.eclipse.jetty"         %   "jetty-server"                % jettyVersion,
        "org.eclipse.jetty"         %   "jetty-webapp"                % jettyVersion,
        "org.mongodb"               %%  "casbah-core"                 % casbahVersion,
        "org.jongo"                 %   "jongo"                       % jongoVersion,
        "com.novus"                 %%  "salat"                       % salatVersion,
        "com.github.simplyscala"    %%  "scalatest-embedmongo"        % embedMongoVersion % "test",
        "com.softwaremill.macwire"  %%  "macros"                      % macwireVersion,
        "com.softwaremill.macwire"  %%  "runtime"                     % macwireVersion,
        "ch.qos.logback"            %   "logback-classic"             % logbackVersion,
        "org.scalamock"             %%  "scalamock-scalatest-support" % scalaMockVersion % "test"
    )
}

seq(Twirl.settings: _*)

mainClass := Some("moe.pizza.pilotquery.PilotProvider")

initialCommands in console := """
    import collection.JavaConversions._
"""

Revolver.settings

assemblySettings

jarName in assembly := "location-provider.jar"
