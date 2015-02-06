name := """play2Sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.projectlombok" % "lombok" % "1.14.8",
  "org.apache.commons" % "commons-email" % "1.3.3",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.1.8",
  "com.google.guava" % "guava" % "18.0",
  "redis.clients" % "jedis" % "2.6.2"
)
