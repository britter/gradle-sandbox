plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("com.example:platform"))
    implementation("org.apache.commons:commons-lang3")
}
