plugins {
    groovy
    `java-test-fixtures`
}

dependencies {
    testFixturesApi(project(":model"))
    testFixturesImplementation("org.codehaus.groovy:groovy:3.0.7")
}