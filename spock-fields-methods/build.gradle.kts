plugins {
    groovy
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-3.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("exec") {
    classpath += sourceSets.test.get().runtimeClasspath
    mainClass.set("com.example.SimpleSpec")
}