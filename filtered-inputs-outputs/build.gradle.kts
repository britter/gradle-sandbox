tasks {
    register("taskA") {
        inputs.files(fileTree("src") { include("input-a.txt") } )
        outputs.files(fileTree("src") { exclude("input**") })

        doLast {
            copy {
                from("src/input-a.txt")
                into("src")
                rename { "output-a.txt" }
            }
        }
    }

    register("taskB") {
        inputs.files(fileTree("src") { include("input-b.txt") })
        outputs.files("$buildDir/output-b.txt")

        doLast {
            copy {
                from("src/input-b.txt")
                into("$buildDir")
                rename { "output-b.txt" }
            }
        }
    }
}
