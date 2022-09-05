package examplebuild.polymorphiccontainer

import org.gradle.api.Named

interface ContainerType : Named {
    companion object {
        const val NAME = "polymorphicContainer"
    }
}