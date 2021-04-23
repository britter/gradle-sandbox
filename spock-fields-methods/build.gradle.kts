plugins {
    groovy
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.codehaus.groovy:groovy:3.0.8")
    testImplementation("org.spockframework:spock-core:2.0-M5-groovy-3.0")
}

tasks.test {
    useJUnitPlatform()
}
