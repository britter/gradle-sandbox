plugins {
    groovy
}

group = "example"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.codehaus.groovy:groovy:3.0.10")
    testImplementation("org.spockframework:spock-core:2.1-groovy-3.0")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
