plugins {
    groovy
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.bmuschko:gradle-docker-plugin:8.1.0")

    testImplementation("org.spockframework:spock-core:2.1-groovy-3.0")
    testImplementation("org.testcontainers:testcontainers:1.17.2")
}

tasks.test {
    useJUnitPlatform()
}
