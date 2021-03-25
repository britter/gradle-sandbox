import org.gradle.jvm.application.tasks.CreateStartScripts

plugins {
    application
}

val extension = objects.domainObjectContainer(ApplicationVariantsExtension::class)
extensions.add("applicationVariants", extension)

extension.all {
    val variantClasspath = files(classpath, tasks.jar, configurations.runtimeClasspath)
    val startScripts by tasks.getting(CreateStartScripts::class)
    val variantStartScripts by tasks.creating(CreateStartScripts::class) {
        applicationName = startScripts.applicationName
        startScripts.mainClass.let { mainClass.set(it) }
        outputDir = startScripts.outputDir
        classpath = variantClasspath
    }

    // duplicating what the application plugin does for each variant
    distributions {
        create(name) {
            contents {
                with(project.copySpec {
                    from(variantClasspath)
                    into("lib")
                })
                with(project.copySpec {
                    from(variantStartScripts)
                    into("bin")
                })
            }
        }
    }

    configurations.create(name)
    artifacts {
        val installVariantDist = tasks.named<Sync>("install${name.capitalize()}Dist")
        add(name, installVariantDist.map { it.destinationDir })
    }
}

artifacts {
    val installDist = tasks.named<Sync>("installDist")
    add("default", installDist.map { it.destinationDir })
}

