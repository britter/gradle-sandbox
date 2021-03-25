plugins {
    id("java-library-conventions")
}

val withUiDist by configurations.creating

dependencies {
    implementation(project(":app"))
    withUiDist(project(":app", "withUi"))
}

tasks.register("dumpCp") {
    inputs.files(withUiDist)
    doFirst {
        withUiDist.forEach { println(it) }
    }
}