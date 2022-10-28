# quarkus-excludes

Example project showing problems with excluding optional dependencies when building quarkus applications.

## Introduction

Following https://blog.codecentric.de/keycloak-x-but-secure-without-vulnerable-libraries we wanted to create a custom Keycloak build
that does not include optional dependencies first and foremost unnecessary database drivers.
By porting the POM file from the blog post above to Gradle we ended up with a build file that has a dependency to `keycloak-quarkus-server` and then excludes for the libraries we don't want:

```kotlin
dependencies {
    implementation("org.keycloak:keycloak-quarkus-server:$keycloakVersion") {
        exclude("mysql", "mysql-connector-java")
        exclude("io.quarkus", "quarkus-jdbc-mysql")
        exclude("io.quarkus", "quarkus-jdbc-mysql-deployment")
        
        // more excludes
    }
}
```

Surprisingly it was pretty difficult to get this working, which is a result of the way the Quarkus Gradle plugin processes dependencies.

## Reproducer

To reproduce the problem:

```
git checkout ebfad8c744bf67151322116e3ee73f451b524c91
./gradlew clean aCK && ./build/keycloak-18.0.2/bin/kc.sh --verbose start-dev
```

This will result in the following error at application runtime:

```bash
Updating the configuration and installing your custom providers, if any. Please wait.
2022-10-28 09:57:07,522 WARN  [io.quarkus.arc.processor.BeanArchives] (build-66) Failed to index org.keycloak.representations.account.CredentialMetadataRepresentation: Class does not exist in ClassLoader QuarkusClassLoader:Deployment Class Loader: PROD@1991f767
2022-10-28 09:57:07,526 WARN  [io.quarkus.deployment.steps.ReflectiveHierarchyStep] (build-66) Unable to properly register the hierarchy of the following classes for reflection as they are not in the Jandex index:
	- org.keycloak.representations.account.CredentialMetadataRepresentation (source: ResteasyServerCommonProcessor > org.keycloak.services.resources.account.AccountCredentialResource[java.util.stream.Stream<org.keycloak.services.resources.account.AccountCredentialResource$CredentialContainer> credentialTypes(java.lang.String type, java.lang.Boolean userCredentials)])
Consider adding them to the index either by creating a Jandex index for your dependency via the Maven plugin, an empty META-INF/beans.xml or quarkus.index-dependency properties.
2022-10-28 09:57:07,781 WARN  [io.quarkus.deployment.steps.ClassTransformingBuildStep] (build-58) Could not remove configured resources from the following artifacts as they were not found in the model: [com.oracle.database.jdbc:ojdbc11::jar]
2022-10-28 09:57:08,304 INFO  [io.quarkus.deployment.QuarkusAugmentor] (main) Quarkus augmentation completed in 2193ms
ERROR: Unexpected error when starting the server in (development) mode
Error details:
java.lang.RuntimeException: Failed to start quarkus
	at io.quarkus.runner.ApplicationImpl.<clinit>(Unknown Source)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
	at java.base/java.lang.Class.newInstance(Class.java:584)
	at io.quarkus.runtime.Quarkus.run(Quarkus.java:66)
	at org.keycloak.quarkus.runtime.KeycloakMain.start(KeycloakMain.java:86)
	at org.keycloak.quarkus.runtime.cli.command.AbstractStartCommand.run(AbstractStartCommand.java:34)
	at picocli.CommandLine.executeUserObject(CommandLine.java:1939)
	at picocli.CommandLine.access$1300(CommandLine.java:145)
	at picocli.CommandLine$RunLast.executeUserObjectOfLastSubcommandWithSameParent(CommandLine.java:2358)
	at picocli.CommandLine$RunLast.handle(CommandLine.java:2352)
	at picocli.CommandLine$RunLast.handle(CommandLine.java:2314)
	at picocli.CommandLine$AbstractParseResultHandler.execute(CommandLine.java:2179)
	at picocli.CommandLine$RunLast.execute(CommandLine.java:2316)
	at picocli.CommandLine.execute(CommandLine.java:2078)
	at org.keycloak.quarkus.runtime.cli.Picocli.parseAndRun(Picocli.java:88)
	at org.keycloak.quarkus.runtime.KeycloakMain.main(KeycloakMain.java:77)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at io.quarkus.bootstrap.runner.QuarkusEntryPoint.doRun(QuarkusEntryPoint.java:60)
	at io.quarkus.bootstrap.runner.QuarkusEntryPoint.main(QuarkusEntryPoint.java:31)
Caused by: java.lang.ClassNotFoundException: io.quarkus.jdbc.mysql.runtime.MySQLAgroalConnectionConfigurer
	at java.base/java.lang.ClassLoader.findClass(ClassLoader.java:719)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:589)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Class.java:398)
	at io.quarkus.jdbc.mysql.runtime.MySQLAgroalConnectionConfigurer_Bean.<init>(Unknown Source)
	at io.quarkus.arc.setup.Default_ComponentsProvider.addBeans2(Unknown Source)
	at io.quarkus.arc.setup.Default_ComponentsProvider.getComponents(Unknown Source)
	at io.quarkus.arc.impl.ArcContainerImpl.<init>(ArcContainerImpl.java:118)
	at io.quarkus.arc.Arc.initialize(Arc.java:24)
	at io.quarkus.arc.runtime.ArcRecorder.getContainer(ArcRecorder.java:40)
	at io.quarkus.deployment.steps.ArcProcessor$generateResources686947423.deploy_0(Unknown Source)
	at io.quarkus.deployment.steps.ArcProcessor$generateResources686947423.deploy(Unknown Source)
	... 25 more
```

As one can see the application tries to load the MySQL driver, although we excluded it from the Keycloak dependency.
This is because `deployment-class-path.dat` is calculated incorrectly because the AppModel contains the wrong set of dependencies.
It's a result of `implementationConfiguration` deriving its dependencies incorrectly from the `implementation` configuration in [ApplicationDeploymentClasspathBuilder.createBuildClasspath](https://github.com/quarkusio/quarkus/blob/3319dba3e10fd00a9212f6379e4763dc0233667f/devtools/gradle/gradle-application-plugin/src/main/java/io/quarkus/gradle/dependency/ApplicationDeploymentClasspathBuilder.java#L29-L57).
The consequence is the excluded dependencies ending up in `quarkusDeploymentConfiguration` later on, as we can see from the Build Scan that was created for this commit: https://scans.gradle.com/s/usxpq6jw7c45g/dependencies?dependencies=mysql&expandAll

## Workaround

A workaround for this problem is to exclude the dependency on all configuration in the project. We can see this working by:

```
git checkout a87ae629979388d0212f578040c0e9e416863219
./gradlew clean aCK && ./build/keycloak-18.0.2/bin/kc.sh --verbose start-dev
```

This time Keycloak will be able to start (although it will crash if not Postgres connection is configured, but that's expected).
By inspecting the Build Scan for this build we can see that we successfully excluded the dependencies now: https://scans.gradle.com/s/i3mnra3vgwdas/dependencies?dependencies=mysql&expandAll

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
