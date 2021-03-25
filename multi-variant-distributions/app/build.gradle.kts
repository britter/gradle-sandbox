import org.gradle.jvm.application.tasks.CreateStartScripts

plugins {
    id("java-application-conventions")
    `application-variants`
}

application {
    // Define the main class for the application.
    mainClass.set("example.App")
}

val ui by configurations.creating

applicationVariants {
    create("withUi") {
        classpath.from(ui)
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.11")
    ui(project(":ui"))
}

tasks.register("dumpConf") {
    doFirst {
        configurations.forEach { println(it) }
    }
}