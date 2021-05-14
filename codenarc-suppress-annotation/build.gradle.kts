plugins {
    groovy
    codenarc
}

codenarc {
    toolVersion = "2.1.0"
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.8")
    codenarc(platform("org.codehaus.groovy:groovy-bom:3.0.8"))
    codenarc("org.codenarc:CodeNarc:2.1.0")
}

tasks.withType(CodeNarc::class) {
    configFile = file("${buildscript.sourceFile?.parentFile}/codenarc/codenarc.groovy")
}