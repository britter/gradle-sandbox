// Workaround as documented in https://github.com/gradle/gradle/issues/18984#issuecomment-969998902
if (gradle.parent == null) {
    includeBuild("../build-logic")
}
