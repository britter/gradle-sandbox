plugins {
    java
}

repositories {
    mavenCentral()
    maven {
        name = "apache-snapshots"
        url = uri("https://repository.apache.org/content/repositories/snapshots/")
    }
}

dependencies.components {
    all<PomRule> {
        params("runtime")
    }
    all<PomRule> {
        params("runtimeElements")
    }
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.2.2-SNAPSHOT")
}

val poms by configurations.creating {
    extendsFrom(configurations["runtimeClasspath"])
    isCanBeResolved = true
    isCanBeConsumed = false
    attributes {
        attribute(Category.CATEGORY_ATTRIBUTE, getObjects().named(Category.DOCUMENTATION))
        attribute(DocsType.DOCS_TYPE_ATTRIBUTE, getObjects().named("pom"))
    }
}

tasks.register<Copy>("downloadPoms") {
    from(poms)
    into("$buildDir/poms")
}

open class PomRule @Inject constructor(private val baseVariant: String) : ComponentMetadataRule {

    @Inject
    open fun getObjects(): ObjectFactory = throw UnsupportedOperationException()

    override fun execute(context: ComponentMetadataContext) {
        context.details.maybeAddVariant("pom", baseVariant) {
            attributes {
                attribute(Category.CATEGORY_ATTRIBUTE, getObjects().named(Category.DOCUMENTATION))
                attribute(DocsType.DOCS_TYPE_ATTRIBUTE, getObjects().named("pom"))
            }
            withFiles {
                removeAllFiles()
                addFile("${context.details.id.name}-${context.details.id.version}.pom")
            }
        }
    }
}

