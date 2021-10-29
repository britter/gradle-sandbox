plugins {
    `java-library`
}

version = "2.9"

configurations.create("model") {
    isCanBeResolved = false
    isCanBeConsumed = true
    attributes {
        attribute(Usage.USAGE_ATTRIBUTE, objects.named("lib-jar"))
        attribute(Attribute.of("com.example.version", String::class.java), "$version")
    }
    artifacts {
        outgoing {
            artifact(tasks.jar)
        }
    }
}