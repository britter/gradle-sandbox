package examplebuild.polymorphiccontainer

import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class Bar(@Inject val n: String) : ContainerType {
    override fun getName() = n

    abstract val bar: Property<Int>
}