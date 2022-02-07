# eager-configuration-artifacts

Reproducer project for an integration problem between `base` plugin and `com.github.psxpaul.execfork`.
As long either `base` is applied in `producer/build.gradle.kts` or `com.github.psxpaul.execfork` is applied in
`build-logic/my-plugin/src/main/kotlin/my-bundle-plugin.gradle.kts`, running `./gradlew tasks` fails with:

```bash
Build file '/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/eager-configuration-artifacts/producer/build.gradle.kts' line: 1

An exception occurred applying plugin request [id: 'my-bundle-plugin']
> Failed to apply plugin 'com.github.psxpaul.execfork'.
   > Could not create task ':producer:assemble'.
      > Expected task 'someTask' output files to contain exactly one file, however, it contains no files.
```

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
