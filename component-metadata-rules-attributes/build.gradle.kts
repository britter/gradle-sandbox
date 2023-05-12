plugins {
    `java-library`
}

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository {
            ivy {
                url = uri("https://github.com/aquasecurity/")
                patternLayout {
                    artifact("[module]/releases/download/v[revision]/[artifact]_[revision]_[classifier].[ext]")
                }
                metadataSources {
                    artifact()
                }
            }
        }
        filter {
            includeModule("aquasecurity", "trivy")
        }
    }
}

val trivy by configurations.creating

dependencies {
    components.all<CustomRule>()

    trivy("aquasecurity:trivy:0.37.3:macOS-ARM64@tar.gz")
    api("org.apache.commons:commons-lang3:3.11")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

abstract class CustomRule : ComponentMetadataRule {

    @get:Inject
    abstract val objects: ObjectFactory

    override fun execute(context: ComponentMetadataContext) {
        println("Library: ${context.details.id.name}")
        context.details.attributes.keySet().forEach {
            println("$it:${context.details.attributes.getAttribute(it)}")
        }
        context.details.allVariants {
            println("Variant:$this")
            attributes.keySet().forEach {
                println("$it:${context.details.attributes.getAttribute(it)}")
            }
        }
    }

}
