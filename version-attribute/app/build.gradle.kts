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
            deps.incoming.artifacts.artifacts.forEach {
                val version = it.variant.attributes.getAttribute(
                    Attribute.of(
                        "com.example.version",
                        String::class.java
                    )
                )
                println(version)
            }
        }
    }
}
