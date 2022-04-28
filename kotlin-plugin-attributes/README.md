# kotlin-plugin-attributes

This is project shows problems resulting from the Kotlin Gradle plugin adding attributes to the `default` configuration.  

When running `./gradlew :consumer:aggregate` the build will fail with (see [this Build Scan](https://scans.gradle.com/s/aa35wede6fezg/failure?anchor=e30&focused-exception-line=0-1-0#1)):

```bash
Execution failed for task ':consumer:aggregate'.
> Not a text file: /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/kotlin-plugin-attributes/kotlin-module/build/libs/kotlin-module.jar
```

Let's see why...

## Project setup

The use case that is modelled here is a project where one module wants to aggregate some files from all other modules that provide a project variant with a certain attribute without having to explicitly depending on those modules.

### `:consumer` module

The `:consumer` module creates a configuration called `aggreagtion` that has a custom attribute called `custom-attribute-type` set to `custom-attribute-value`.
Then it declares a dependency to each other module in this configuration.
Because resolving this configuration would fail if a module does not provide a matching variant, a lenient artifact view is used.
Last the `:consumer:aggregate` task will resolve the artifact view and throw an exception if a non `.txt` is being resolved.   

### `:producer` module

The `:producer` module creates a matching configuration used for publishing a project variant called `publication`.
This variant sets attributes to match what is defined in `:consumer`.
A simple task writing a text file is used to generate some outgoing artifacts.

### `:kotlin-module` module

The `:kotlin-module` is here to show the problematic behavior of the Kotlin Gradle plugin.
This module does not provide a matching project variant.
It just applied the Kotlin Gradle plugin and should therefore not be matched in `:consumer`.

## The problem

As described above, when running `./gradlew :consumer:aggregate` the build will fail.
This is because Gradle finds a match for `aggreagtion` in `:kotlin-module` although it shouldn't.
What happens is that the deprecated `default` variant that is created by Gradle is matched, although it does not provide matching attributes:

```bash
$ ./graldew :consumer:dependencies --configuration=aggregation
> Task :consumer:dependencies

------------------------------------------------------------
Project ':consumer'
------------------------------------------------------------

aggregation
+--- project :kotlin-module
|    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0
|         +--- org.jetbrains.kotlin:kotlin-stdlib:1.6.0
|         |    +--- org.jetbrains:annotations:13.0
|         |    \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.6.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.0
|              \--- org.jetbrains.kotlin:kotlin-stdlib:1.6.0 (*)
\--- project :producer

(*) - dependencies omitted (listed previously)
```

Using `dependencyInsights` task we can see why:

```bash
$ ./gradlew :consumer:dependencyInsight --configuration=aggregation --dependency=kotlin-module

> Task :consumer:dependencyInsight
project :kotlin-module
   variant "default" [
      org.jetbrains.kotlin.platform.type = jvm (not requested)
      org.gradle.jvm.environment         = standard-jvm (not requested)
      Requested attributes not found in the selected variant:
         org.gradle.category                = documentation
         org.gradle.usage                   = custom-aggregation-for-reporting
         custom-attribute-type              = custom-attribute-value
   ]

project :kotlin-module
\--- aggregation
```

If the Kotlin Gradle plugin is commented out from `kotlin-module/build.gradle.kts` the situation changes.
The build is now successful, see [this Build Scan](https://scans.gradle.com/s/y3asnw77kmfns).
Using the `dependencies` and `dependencyInsight` tasks we can see what changed:

```bash
$ ./gradlew :consumer:dependencies --configuration=aggregation

> Task :consumer:dependencies

------------------------------------------------------------
Project ':consumer'
------------------------------------------------------------

aggregation
+--- project :kotlin-module FAILED
\--- project :producer
```

This says that `:kotlin-module` was not matched anymore.
Let's see why:

```bash
$ ./gradlew :consumer:dependencyInsight --configuration=aggregation --dependency=kotlin-module

> Task :consumer:dependencyInsight
project :kotlin-module FAILED
   Failures:
      - Could not resolve project :kotlin-module.
          - No matching variant of project :kotlin-module was found. The consumer was configured to find attribute 'org.gradle.category' with value 'documentation', attribute 'org.gradle.usage' with value 'custom-aggregation-for-reporting', attribute 'custom-attribute-type' with value 'custom-attribute-value' but:
              - Variant 'apiElements' capability kotlin-plugin-attributes:kotlin-module:unspecified:
                  - Incompatible because this component declares attribute 'org.gradle.category' with value 'library', attribute 'org.gradle.usage' with value 'java-api' and the consumer needed attribute 'org.gradle.category' with value 'documentation', attribute 'org.gradle.usage' with value 'custom-aggregation-for-reporting'
                  - Other compatible attribute:
                      - Doesn't say anything about custom-attribute-type (required 'custom-attribute-value')
              - Variant 'mainSourceElements' capability kotlin-plugin-attributes:kotlin-module:unspecified:
                  - Incompatible because this component declares attribute 'org.gradle.category' with value 'verification' and the consumer needed attribute 'org.gradle.category' with value 'documentation'
                  - Other compatible attributes:
                      - Doesn't say anything about custom-attribute-type (required 'custom-attribute-value')
                      - Doesn't say anything about org.gradle.usage (required 'custom-aggregation-for-reporting')
              - Variant 'runtimeElements' capability kotlin-plugin-attributes:kotlin-module:unspecified:
                  - Incompatible because this component declares attribute 'org.gradle.category' with value 'library', attribute 'org.gradle.usage' with value 'java-runtime' and the consumer needed attribute 'org.gradle.category' with value 'documentation', attribute 'org.gradle.usage' with value 'custom-aggregation-for-reporting'
                  - Other compatible attribute:
                      - Doesn't say anything about custom-attribute-type (required 'custom-attribute-value')
              - Variant 'testResultsElementsForTest' capability kotlin-plugin-attributes:kotlin-module:unspecified:
                  - Incompatible because this component declares attribute 'org.gradle.category' with value 'verification' and the consumer needed attribute 'org.gradle.category' with value 'documentation'
                  - Other compatible attributes:
                      - Doesn't say anything about custom-attribute-type (required 'custom-attribute-value')
                      - Doesn't say anything about org.gradle.usage (required 'custom-aggregation-for-reporting')

project :kotlin-module FAILED
\--- aggregation
```

## The solution

The Kotlin Gradle plugin should not add attributes to configuration it does not own, at the very least not to the ones that are created by Gradle and are deprecated like `default` and `archives`.

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
