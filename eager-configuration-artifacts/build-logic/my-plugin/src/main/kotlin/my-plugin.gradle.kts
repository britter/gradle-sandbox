import myplugin.MyExtension
import myplugin.SomeTask

val extension = extensions.create("myExtension", MyExtension::class)

val outputRoot = layout.buildDirectory.dir("root")

val someTask by tasks.registering(SomeTask::class) {
    content.convention("Hello World")
    outputDirectory.convention(outputRoot.flatMap { it.dir(extension.outputDirectoryName) })
}

val publication by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    outgoing.artifact(someTask)
    attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named("example"))
}
