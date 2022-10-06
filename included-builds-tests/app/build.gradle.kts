plugins {
    groovy
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.codehaus.groovy:groovy:3.0.10")

    testImplementation("org.spockframework:spock-core:2.1-groovy-3.0")
    testImplementation("junit:junit:4.13.2")

    implementation("com.google.guava:guava:31.0.1-jre")
}

application {
    // Define the main class for the application.
    mainClass.set("com.example.App")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
