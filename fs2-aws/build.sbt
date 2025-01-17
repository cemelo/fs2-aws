name := "fs2-aws"

// coverage
coverageMinimum := 40
coverageFailOnMinimum := true

scalaVersion := "2.13.1"

releasePublishArtifactsAction := PgpKeys.publishSigned.value

val fs2Version    = "2.0.1"
val AwsSdkVersion = "1.11.650"
val cirisVersion  = "0.13.0-RC1"

libraryDependencies ++= Seq(
  "co.fs2"                  %% "fs2-core"                     % fs2Version,
  "co.fs2"                  %% "fs2-io"                       % fs2Version,
  "org.typelevel"           %% "alleycats-core"               % "2.0.0",
  "com.amazonaws"           % "aws-java-sdk-kinesis"          % AwsSdkVersion,
  "com.amazonaws"           % "aws-java-sdk-s3"               % AwsSdkVersion,
  "com.amazonaws"           % "aws-java-sdk-sqs"              % AwsSdkVersion,
  "com.amazonaws"           % "amazon-kinesis-producer"       % "0.13.1",
  "software.amazon.kinesis" % "amazon-kinesis-client"         % "2.2.4",
  "org.scalatest"           %% "scalatest"                    % "3.0.8" % Test,
  "org.mockito"             % "mockito-core"                  % "3.1.0" % Test,
  "com.amazonaws"           % "aws-java-sdk-sqs"              % AwsSdkVersion excludeAll ("commons-logging", "commons-logging"),
  "com.amazonaws"           % "amazon-sqs-java-messaging-lib" % "1.0.8" excludeAll ("commons-logging", "commons-logging"),
  "is.cir"                  %% "ciris-core"                   % cirisVersion,
  "is.cir"                  %% "ciris-enumeratum"             % cirisVersion,
  "is.cir"                  %% "ciris-refined"                % cirisVersion,
  "eu.timepit"              %% "refined"                      % "0.9.10"
)
