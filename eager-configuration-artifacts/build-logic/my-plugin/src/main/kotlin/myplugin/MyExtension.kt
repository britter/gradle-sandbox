package myplugin

import org.gradle.api.provider.Property

interface MyExtension {

    val outputDirectoryName: Property<String>
}