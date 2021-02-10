plugins {
    distribution
}

tasks {
    val webpack by registering {
        inputs.files(fileTree("src/main").exclude("**/__tests__/**"))
            .withPropertyName("uiSourceDirectory")
            .withPathSensitivity(PathSensitivity.RELATIVE)

        outputs.dir("$buildDir/webpack")

        doLast {
            project.copy {
                from("src/main") {
                    exclude("**/__tests__/**")
                }
                into("$buildDir/webpack")
            }
        }
    }

    val updateSnapshots by registering {
        inputs.files(fileTree("src/main") { exclude("**.js.snap") })
            .withPropertyName("source")
            .withPathSensitivity(PathSensitivity.RELATIVE)

        outputs.files(fileTree("src/main") { include("**.js.snap") })
            .withPropertyName("snapshots")

        doLast {
            println("Checking snapshot files")
        }
    }
}

distributions {
    main {
        contents {
            from(tasks["webpack"])
        }
    }
}