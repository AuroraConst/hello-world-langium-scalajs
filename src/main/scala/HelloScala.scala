import typings.helloWorld.anon.HelloWorld
// import typings.langium.libWorkspaceFileSystemProviderMod.EmptyFileSystem
// import typings.langium.mod.EmptyFileSystem
import typings.langium.libMod.EmptyFileSystem
import typings.langium.libLspDefaultLspModuleMod.DefaultSharedModuleContext
import typings.helloWorld.outLanguageHelloWorldModuleMod.createHelloWorldServices


// import typings.langium.libWorkspaceMod.EmptyFileSystem
import typings.langium.testMod.parseHelper
import typings.helloWorld.outLanguageGeneratedAstMod.Model


object  HelloScala :
  @main def run() =
    val services = createHelloWorldServices( EmptyFileSystem.asInstanceOf[DefaultSharedModuleContext])
    val parse = parseHelper[Model](services.HelloWorld) 
    println("Hello Scala 3! there")