# intellij-included-platforms

Sample project showing a bug in IntelliJ which causes the project synchronization to fail.

## Context

If Gradle users want to make sure they are using the same versions for their main build, and the build logic defined in buildSrc, one way to do this is to introduce an included build that produces a dependency platform.
That platform can then be imported in modules of the main build, and the buildSrc build.

## Problem

If this project is synchronized in IntelliJ 2021.1 Beta it will fail with:

```shell
Execution failed for task ':buildSrc:generateSourceRoots'.
> Failed to calculate the value of task ':buildSrc:generateSourceRoots' property 'sourceRoots'.
   > Project with path ':platform' could not be found in project ':buildSrc'.
```

The reason seems to be that this build has a buildSrc build and that build imports a platform that is build by an included build.
The platform used is defined in `included-build/platform`, which is a submodule of the `included-build` build.

Gradle can build this project without issues, see https://scans.gradle.com/s/ie5gvg2dw2uha.

## Workaround

One way to make this working is by not having the platform as a submodule of an included build.
If the platform is directly included as an included build, IntelliJ can import the project again.
Try this out by changing this line in `./settings.gradle.kts`:

```diff
diff --git a/intellij-included-platforms/settings.gradle.kts b/intellij-included-platforms/settings.gradle.kts
index 93106be..a8687ac 100644
--- a/intellij-included-platforms/settings.gradle.kts
+++ b/intellij-included-platforms/settings.gradle.kts
@@ -1,4 +1,4 @@
 rootProject.name = "intellij-included-platforms"
 
 include("lib")
-includeBuild("included-build")
+includeBuild("included-build/platform")
```
