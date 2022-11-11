package mybuild

import org.gradle.api.Named
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class DockerApplicationExtension @Inject constructor(private val n: String) : Named {

    override fun getName() = n

    abstract val imageName: Property<String>
}