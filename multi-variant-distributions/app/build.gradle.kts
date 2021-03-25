plugins {
    id("java-application-conventions")
}

application {
    // Define the main class for the application.
    mainClass.set("example.App")
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.11")
}

artifacts {
    val installDist by tasks.existing(Sync::class)
    add("default", installDist.map { it.destinationDir })
}