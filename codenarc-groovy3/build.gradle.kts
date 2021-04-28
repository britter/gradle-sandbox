plugins {
    groovy
    codenarc
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.8")
}

tasks.withType(CodeNarc::class) {
    configFile = file("${buildscript.sourceFile?.parentFile}/codenarc/codenarc.groovy")
}