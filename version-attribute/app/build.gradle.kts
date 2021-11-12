plugins {
 application
}

application {
    mainClass.set("com.example.App")
}

val deps by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named("lib-jar"))
    }
}

dependencies {
    deps(project(":lib"))
    deps(project(":model"))
}

tasks {
    register("dump") {
        doFirst {
            deps.files.forEach { println(it) }
            println("resolvedConfiguration")
            deps.resolvedConfiguration.resolvedArtifacts.forEach {
                println("name: ${it.name}")
                println("moduleVersion.id.version: ${it.moduleVersion.id.version}")
                println("================================================")
            }
            println("incoming.dependencies")
            deps.incoming.dependencies.forEach {
                println("version: ${it.version}")
                println("================================================")
            }
            println("incoming.resolutionResult.allComponents")
            deps.incoming.resolutionResult.allComponents.forEach {
                println("id.displayName: ${it.id.displayName}")
                println("moduleVersion.version: ${it.moduleVersion!!.version}")
                println("================================================")
            }
            println("incoming.artifacts.artifacts")
            deps.incoming.artifacts.artifacts.forEach {
                println("id.displayName: ${it.id.displayName}")
                println("id.componentIdentifier.displayName: ${it.id.componentIdentifier.displayName}")
                println("variant.displayName: ${it.variant.displayName}")
                val version = it.variant.attributes.getAttribute(
                    Attribute.of(
                        "com.example.version",
                        String::class.java
                    )
                )
                println(version)
                println("================================================")
            }
        }
    }
}
