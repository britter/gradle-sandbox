# included-builds-tests

This is a reproducer project for https://youtrack.jetbrains.com/issue/IDEA-307111

## Project structure

The project has an included build called `included-build` with a single module.
It also has a `buildSrc` module which can be to define build logic.
The `buildSrc` build has a dependency on the module produced by `included-build`.
The use case for such a build setup is a gradual migration from `buildSrc` to managing build logic in included builds:
Sometimes you simply can not move all of `buildSrc` into an included build at once.
Instead, you need to move a few things first and continue using them in `buildSrc` by defining a dependency. 

## Problem

If a dependency from an `buildSrc` to an included build is present, IntelliJ fails to capture test events from tests started inside that included build module.
Also, it's not possible to debug in that module anymore.
To reproduce try commenting in and out the dependency in `buildSrc/build.gradle.kts` and then running `included-build/module/src/test/groovy/com/example/included/ExampleSpec.groovy` from the IDE.

Running **without** the dependency:

![01-test-run-without-buildSrc-dependency](https://github.com/britter/gradle-sandbox/blob/main/included-builds-tests/screenshots/01-test-run-without-buildSrc-dependency.png)

Running **with** the dependency:

![02-test-run-with-buildSrc-dependency](https://github.com/britter/gradle-sandbox/blob/main/included-builds-tests/screenshots/02-test-run-with-buildSrc-dependency.png)

As you can see, IntelliJ does not capture any test events in the second case.

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
