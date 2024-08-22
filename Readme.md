## package npm to local file
### creates a tarball at the current directory which eventually needs to be copied into target/scala-3.3.3/scalajs-bundler/main/lib
npm pack --packdestination ./lib_langium

## copy packed library from above to target directory with sbt task
copyLangiumTgz

## Emit d.ts files
### tsconfig.json
```
    "compilerOptions": {
        "target": "ES2017",
        "module": "Node16",
        "lib": [
            "ESNext",
            "DOM",
            "WebWorker"
        ],
        "declaration": true,   //*** emit d.ts files from typescript*************************
        "sourceMap": true,
        "outDir": "out",
        "strict": true,
        "noUnusedLocals": true,
        "noImplicitReturns": true,
        "noImplicitOverride": true,
        "moduleResolution": "Node16",
        "esModuleInterop": true,
        "skipLibCheck": true,
        "forceConsistentCasingInFileNames": true,
        "rootDir": ".",
        "noEmit": true
    },
```

## Local npm dependency in sbt
### build.sbt
```
.settings(

    name := "hello-world",
    
    Compile /npmDependencies ++= Seq(
      "hello-world" -> "./lib_langium/hello-world-0.0.1.tgz"
    ),
```


## Further Reading regarding npm  sbt
https://www.toptal.com/scala/using-scala-js-with-npm-and-browserify