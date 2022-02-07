val consumingConf by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
    attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named("example"))
}

dependencies {
    consumingConf(project(":producer"))
}

tasks.register<Copy>("copyFile") {
    from(consumingConf)
    into(layout.buildDirectory.dir("some-dir"))
}