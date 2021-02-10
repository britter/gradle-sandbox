# Sample for filtered inputs and outputs

This shows how two task while having distinct inputs and outputs are nevertheless reported
as consuming each others outputs without explicit task dependency.
The reason seems to be that the filters applied to `ConfigurableFileTree` are not taken into account.

- `taskA` consumes file `src/input-a.txt` and produces `src/output-a.txt`
- `taskB` consumes file `src/input-b.txt` and produces `build/output-a.txt`

When running `./gradlew taskA taskB` it will produce the following deprecation warning:

```shell
> Task :taskB
Validation failed for task ':taskB', disabling optimizations:
  - Task ':taskB' uses the output of task ':taskA', without declaring an explicit dependency (using Task.dependsOn() or Task.mustRunAfter()) or an implicit dependency (declaring task ':taskA' as an input). This can lead to incorrect results being produced, depending on what order the tasks are executed.
```

See also https://gradle.com/s/3bewwb6h43imc