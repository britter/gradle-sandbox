import org.gradle.api.file.ConfigurableFileCollection

abstract class ApplicationVariantsExtension(val name: String) {
    abstract val classpath: ConfigurableFileCollection
}