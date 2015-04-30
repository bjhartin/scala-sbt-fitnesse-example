import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object ScalatraSlickBuild extends Build {
  val Organization = "com.example"
  val Name = "TDD Example"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.5"
  val ScalatraVersion = "2.4.0.M2"

  lazy val project = Project (
    "tdd-example",
    file("."),
    settings = Defaults.defaultConfigs ++ ScalatraPlugin.scalatraSettings ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      retrieveManaged := true,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "org.fitnesse" % "fitnesse" % "20150424" % "test",
        "org.fitnesse" % "fitlibrary" % "20080812" % "test",
        "net.sourceforge.jwebunit" % "jwebunit-core" % "3.2" % "test",
        "net.sourceforge.jwebunit" % "jwebunit-htmlunit-plugin" % "3.2" % "test",
        "junit" % "junit" % "4.12" % "test",
        "com.novocode" % "junit-interface" % "0.8" % "test->default",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "com.typesafe.slick" %% "slick" % "3.0.0-RC1",
        "com.h2database" % "h2" % "1.4.181",
        "com.mchange" % "c3p0" % "0.9.2",
        "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq.empty,  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
