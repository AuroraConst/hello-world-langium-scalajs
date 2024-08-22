
ThisBuild / scalaVersion := "3.3.3"

import org.scalajs.linker.interface.ModuleSplitStyle
import Dependencies._


import Path.rebase

ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"


val copyLangiumTgz = taskKey[Boolean]("Links objects into a shared library.")


copyLangiumTgz := {
  val src = baseDirectory.value / "lib_langium" 
  val libfiles = (src ** "*.tgz").get()

  val compileCrossTarget = (Compile / crossTarget ).value / "scalajs-bundler"/"main" /"lib_langium"
  
  val pairs = libfiles pair rebase(src, compileCrossTarget)

  // Copy files to source files to target
  IO.copy(pairs, CopyOptions.apply(overwrite = true, preserveLastModified = true, preserveExecutable = false))


  println(s"${src}")
  println(s"$libfiles")
  println(s"$compileCrossTarget")


  true
}



lazy val root = (project in file("."))
  .enablePlugins(ScalablyTypedConverterPlugin)
  .enablePlugins(ScalaJSPlugin)
  .settings(

    name := "hello-world",
    

// Ensure task is run before package
    // `package` := (`package` dependsOn copyLangiumTgz).value,

    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)
      .withModuleSplitStyle(ModuleSplitStyle.SmallestModules) 
    },

  
    Compile /npmDependencies ++= Seq(
      "hello-world" -> "./lib_langium/hello-world-0.0.1.tgz"
    ),
  )
