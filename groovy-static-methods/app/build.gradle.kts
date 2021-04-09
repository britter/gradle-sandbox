plugins {
    groovy
}

dependencies {
    testImplementation("org.spockframework:spock-core:2.0-M5-groovy-3.0")
    testImplementation(testFixtures(project(":data")))
    testImplementation(project(":model"))
}

tasks.test {
    useJUnitPlatform()
}
