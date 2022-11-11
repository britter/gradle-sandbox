# docker-plugin-conventions-issue

Reproducer project for https://github.com/bmuschko/gradle-docker-plugin/issues/1119.

The gradle-docker-plugin uses `set` inside the constructors of quite a few tasks.
This causes conventions to not work anymore.

## Project layout

In this example project I want to build a custom docker application project on top of the gradle-docker-plugin.
My plugin should provide a custom DSL to build authors.
Layout of this project:

```
|-- app
|   |-- build.gradle.kts <2>
|   `-- src
|       `-- docker
|           `-- simple <3>
|               |-- Dockerfile
|               `-- entrypoint.sh
|-- gradle
|   |-- plugins
|   |   |-- docker-application-plugin
|   |   |   |-- build.gradle.kts
|   |   |   `-- src
|   |   |       `-- main
|   |   |           `-- kotlin
|   |   |               |-- mybuild
|   |   |               |   `-- DockerApplicationExtension.kt
|   |   |               `-- mybuild.docker-application.gradle.kts <1>
|   |   `-- settings.gradle.kts
|   `-- wrapper
|       |-- gradle-wrapper.jar
|       `-- gradle-wrapper.properties
|-- gradlew
|-- gradlew.bat
`-- settings.gradle.kts
```

1. The custom docker plugin is build by an included build. It registers a project extension that provides the custom DSL. The DSL allows for registering multiple docker applications given a name. It then registers and configures the DockerBuildImage task for each application. As a _convention_ build authors can put their docker configuration under `src/docker/<name>`. But they may override this by reconfiguring the DockerBuildImage task.
2. The plugin is applied to the app module. A Docker application called 'simple' is created.
3. The files for the 'simple' application are put into the conventional location which is `src/docker/simple`.

## The problem

Setting a convention is the idiomatic way to provide a default value to a property.
The convention will be used when no other value has been provided via `set` or if `set(null)` was called.
Since `DockerBuildImage` (and other tasks) call `set` on the properties they own, a convention will never be taken into account.
This results in running `./gradlew :app:buildSimpleImage` failing with

```
FAILURE: Build failed with an exception.

* What went wrong:
A problem was found with the configuration of task ':app:buildSimpleImage' (type 'DockerBuildImage').
  - Type 'com.bmuschko.gradle.docker.tasks.image.DockerBuildImage' property 'inputDir' specifies directory '/Users/bene/github/britter/gradle-sandbox/docker-plugin-conventions-issue/app/build/docker' which doesn't exist.

    Reason: An input file was expected to be present but it doesn't exist.

    Possible solutions:
      1. Make sure the directory exists before the task is called.
      2. Make sure that the task which produces the directory is declared as an input.

    Please refer to https://docs.gradle.org/7.5.1/userguide/validation_problems.html#input_file_does_not_exist for more details about this problem.
```

https://scans.gradle.com/s/2g2sflbgm76cc/failure#1

## Solution

1. Only use convention to set default values of properties.
2. Calls to `empty()` on `ListProperties` and `MapProperties` should be dropped without replacement since the default value for these types already is an empty List/Map already and calling `empty()` result in `set(emptyList())` just breaks setting conventions.
3. Tasks should not make a decision about how they are used, so default values should not be set in their initializer. Instead, task implementations should only define the properties and plugins registering those tasks should set the conventions on those tasks for that particular use case of a task.

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
