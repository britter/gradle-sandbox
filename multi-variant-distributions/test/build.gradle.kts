plugins {
    id("java-library-conventions")
}

val appDist by configurations.creating

dependencies {
    implementation(project(":app"))
    appDist(project(":app", "default"))
}

tasks.register("dumpCp") {
    inputs.files(appDist)
    doFirst {
        appDist.forEach { println(it) }
    }
}