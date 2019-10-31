name := "fs2-aws"
organization in ThisBuild := "io.github.dmateusp"

scalaVersion := "2.13.1"

scalacOptions in ThisBuild ++= Seq(
  "-target:jvm-1.8",
  "-encoding",
  "UTF-8", // source files are in UTF-8
  "-deprecation", // warn about use of deprecated APIs
  "-unchecked", // warn about unchecked type parameters
  "-feature", // warn about misused language features
  "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
  "-language:implicitConversions", // allow use of implicit conversions
  "-Xlint", // enable handy linter warnings
  "-Xfatal-warnings", // turn compiler warnings into errors
  "-Ypartial-unification" // allow the compiler to unify type constructors of different arities
)
lazy val root = (project in file("."))
  .aggregate(`fs2-aws`, `fs2-aws-testkit`)
  .settings(
    skip in publish := true
  )

lazy val `fs2-aws`         = (project in file("fs2-aws"))
lazy val `fs2-aws-testkit` = (project in file("fs2-aws-testkit")).dependsOn(`fs2-aws`)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)

// publish
publishTo in ThisBuild := {
  if (isSnapshot.value)
    Some("AvantStay Snapshots" at "https://maven.avantstay.rocks/repository/avantstay-snapshots/")
  else Some("AvantStay Releases" at "https://maven.avantstay.rocks/repository/avantstay-releases/")
}
credentials += Credentials(Path.userHome / ".sbt" / "credentials")

licenses in ThisBuild := Seq(
  "MIT" -> url("https://github.com/dmateusp/fs2-aws/blob/master/LICENSE"))
developers in ThisBuild := List(
  Developer(id = "dmateusp",
            name = "Daniel Mateus Pires",
            email = "dmateusp@gmail.com",
            url = url("https://github.com/dmateusp"))
)
homepage in ThisBuild := Some(url("https://github.com/dmateusp/fs2-aws"))
scmInfo in ThisBuild := Some(
  ScmInfo(url("https://github.com/dmateusp/fs2-aws"),
          "scm:git:git@github.com:dmateusp/fs2-aws.git"))

// release
import ReleaseTransformations._

// signed releases
releasePublishArtifactsAction in ThisBuild := PgpKeys.publishSigned.value
credentials in ThisBuild += Credentials("Sonatype Nexus Repository Manager",
                                        "oss.sonatype.org",
                                        sys.env.getOrElse("SONATYPE_USERNAME", ""),
                                        sys.env.getOrElse("SONATYPE_PASSWORD", ""))

publishArtifact in ThisBuild in Test := true

// release steps
releaseProcess := Seq[ReleaseStep](
  inquireVersions,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  pushChanges
)

releaseTagComment := s"Releasing ${(version in ThisBuild).value}"
releaseCommitMessage := s"[skip travis] Setting version to ${(version in ThisBuild).value}"
