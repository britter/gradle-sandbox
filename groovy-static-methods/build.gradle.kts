plugins {
    groovy
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.codehaus.groovy:groovy:3.0.7")
    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-3.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("exec") {
    classpath += sourceSets.main.get().runtimeClasspath
    mainClass.set("com.example.Foo")
}