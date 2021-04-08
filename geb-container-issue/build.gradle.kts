plugins {
    groovy
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.spockframework:spock-core:2.0-M5-groovy-3.0")
    testImplementation("org.gebish:geb-core:5.0-milestone-1")
    testImplementation("org.gebish:geb-spock:5.0-milestone-1")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
    testImplementation("org.seleniumhq.selenium:selenium-support:3.141.59")
}


tasks.test {
    useJUnitPlatform()

    val chromeDriverFilename = "chromedriver"
    val driverDir = file("${gradle.gradleUserHomeDir}/webdriver/chromedriver")
    systemProperties["webdriver.chrome.driver"] = File(driverDir, chromeDriverFilename).absolutePath
}