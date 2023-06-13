plugins {
    id("com.gradle.enterprise") version "3.13.3"
}
rootProject.name = "build-service-build-finished"

val service = gradle.sharedServices.registerIfAbsent("myService", MyService::class.java) { }

println("${service.get()}")
gradleEnterprise.buildScan.buildFinished {
    println("${service.get()}")
}

abstract class MyService : BuildService<BuildServiceParameters.None>
