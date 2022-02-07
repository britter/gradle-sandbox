import com.github.psxpaul.task.ExecFork
import org.gradle.kotlin.dsl.version

plugins {
    id("my-plugin")
    id("com.github.psxpaul.execfork")
}

tasks.register<ExecFork>("runInBackground")
