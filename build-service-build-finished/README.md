# Reproducer for problem with build services and buildScan.buildFinished

In the settings script we register a build service that we want to use later on in buildScan.buildFinished.
We print the service identity to stdout.
When running `./gradlew help` on this project one can see that `buildFinished` will see a different service instance:

```bash
❯ ./gradlew help
Settings_gradle$MyService$Inject@4be1ba30

> Task :help

Welcome to Gradle 8.1.1.

To run a build, run gradlew <task> ...

To see a list of available tasks, run gradlew tasks

To see more detail about a task, run gradlew help --task <task>

To see a list of command-line options, run gradlew --help

For more detail on using Gradle, see https://docs.gradle.org/8.1.1/userguide/command_line_interface.html

For troubleshooting, visit https://help.gradle.org

BUILD SUCCESSFUL in 1s
1 actionable task: 1 executed
Settings_gradle$MyService$Inject@164f7e59
```
