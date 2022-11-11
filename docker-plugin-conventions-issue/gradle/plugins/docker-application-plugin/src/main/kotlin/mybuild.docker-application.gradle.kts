import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import mybuild.DockerApplicationExtension

val extension = container(DockerApplicationExtension::class.java)
extensions.add("dockerApplications", extension)

extension.all {
    tasks.register<DockerBuildImage>("build${name.capitalize()}Image") {
        imageName.convention(this@all.imageName)
        inputDir.convention(layout.projectDirectory.dir("src/docker/$name"))
    }
}
