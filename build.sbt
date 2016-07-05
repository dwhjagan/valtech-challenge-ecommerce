// Build file for Peter Pilgrim for Valtech Challenge Ecommerce hacker ranker test July 2016

// Define the project name
name := "valtech-challenge-ecommerce"

// Define the version
version := "1.0"

// What version of Scala do we need?
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
   "junit" % "junit" % "4.12" % "test",
      "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
         "com.novocode" % "junit-interface" % "0.11" % "test->default",
	    "org.mockito" % "mockito-all" % "1.10.8" % "test"
	    )


// mainClass in (Compile,run) := Some("uk.co.xenonique.client.timeout.ContentDemo")

// fork a new JVM for 'run' and 'test:run'
fork := true

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

// add a JVM option to use when forking a JVM for 'run'
javaOptions += "-Xmx512M"

