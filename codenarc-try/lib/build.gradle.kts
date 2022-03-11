plugins {
    groovy
    codenarc
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.9")
    codenarc("org.codenarc:CodeNarc:2.2.0")
    codenarc(platform("org.codehaus.groovy:groovy-bom:3.0.9"))
}

codenarc {
    config = project.resources.text.fromString("""
        ruleset {
            BracesForTryCatchFinally
        }
    """.trimIndent())
}
