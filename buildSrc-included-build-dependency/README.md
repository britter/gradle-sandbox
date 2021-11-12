# buildSrc-included-build-dependency

Sample for showing problems with Intellij sync when buildSrc depends on a module from an included build.
Synchronizing this project in IntelliJ 2021.2.3 will fail with:

```
Execution failed for task ':buildSrc:generateSourceRoots'.
> Error while evaluating property 'sourceRoots' of task ':buildSrc:generateSourceRoots'
   > Failed to calculate the value of task ':buildSrc:generateSourceRoots' property 'sourceRoots'.
      > Project with path ':build-logic-module' could not be found in project ':buildSrc'.

* Try:
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':buildSrc:generateSourceRoots'.
	at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:38)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:199)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:157)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.internal.operations.DefaultBuildOperationExecutor.call(DefaultBuildOperationExecutor.java:73)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
	at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:74)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:402)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:389)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:382)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:368)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.lambda$run$0(DefaultPlanExecutor.java:127)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:191)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.executeNextNode(DefaultPlanExecutor.java:182)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:124)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
	at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:61)
Caused by: org.gradle.api.internal.tasks.properties.PropertyEvaluationException: Error while evaluating property 'sourceRoots' of task ':buildSrc:generateSourceRoots'
	at org.gradle.api.internal.tasks.properties.InputParameterUtils.prepareInputParameterValue(InputParameterUtils.java:33)
	at org.gradle.api.internal.tasks.execution.TaskExecution.lambda$visitRegularInputs$1(TaskExecution.java:312)
	at org.gradle.internal.execution.fingerprint.impl.DefaultInputFingerprinter$InputCollectingVisitor.visitInputProperty(DefaultInputFingerprinter.java:95)
	at org.gradle.api.internal.tasks.execution.TaskExecution.visitRegularInputs(TaskExecution.java:312)
	at org.gradle.internal.execution.fingerprint.impl.DefaultInputFingerprinter.fingerprintInputProperties(DefaultInputFingerprinter.java:54)
	at org.gradle.internal.execution.steps.CaptureStateBeforeExecutionStep.captureExecutionStateWithOutputs(CaptureStateBeforeExecutionStep.java:193)
	at org.gradle.internal.execution.steps.CaptureStateBeforeExecutionStep.lambda$captureExecutionState$1(CaptureStateBeforeExecutionStep.java:141)
	at org.gradle.internal.execution.steps.BuildOperationStep$1.call(BuildOperationStep.java:37)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:199)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:157)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.internal.operations.DefaultBuildOperationExecutor.call(DefaultBuildOperationExecutor.java:73)
	at org.gradle.internal.execution.steps.BuildOperationStep.operation(BuildOperationStep.java:34)
	at org.gradle.internal.execution.steps.CaptureStateBeforeExecutionStep.captureExecutionState(CaptureStateBeforeExecutionStep.java:130)
	at org.gradle.internal.execution.steps.CaptureStateBeforeExecutionStep.lambda$execute$0(CaptureStateBeforeExecutionStep.java:75)
	at org.gradle.internal.execution.steps.CaptureStateBeforeExecutionStep.execute(CaptureStateBeforeExecutionStep.java:75)
	at org.gradle.internal.execution.steps.CaptureStateBeforeExecutionStep.execute(CaptureStateBeforeExecutionStep.java:50)
	at org.gradle.internal.execution.steps.SkipEmptyWorkStep.lambda$execute$2(SkipEmptyWorkStep.java:93)
	at org.gradle.internal.execution.steps.SkipEmptyWorkStep.execute(SkipEmptyWorkStep.java:93)
	at org.gradle.internal.execution.steps.SkipEmptyWorkStep.execute(SkipEmptyWorkStep.java:34)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsStartedStep.execute(MarkSnapshottingInputsStartedStep.java:38)
	at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:43)
	at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:31)
	at org.gradle.internal.execution.steps.AssignWorkspaceStep.lambda$execute$0(AssignWorkspaceStep.java:40)
	at org.gradle.api.internal.tasks.execution.TaskExecution$3.withWorkspace(TaskExecution.java:284)
	at org.gradle.internal.execution.steps.AssignWorkspaceStep.execute(AssignWorkspaceStep.java:40)
	at org.gradle.internal.execution.steps.AssignWorkspaceStep.execute(AssignWorkspaceStep.java:30)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:37)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:27)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:44)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:33)
	at org.gradle.internal.execution.impl.DefaultExecutionEngine$1.execute(DefaultExecutionEngine.java:76)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:142)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:131)
	at org.gradle.api.internal.tasks.execution.CleanupStaleOutputsExecuter.execute(CleanupStaleOutputsExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
	at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
	at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
	at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:56)
	at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:199)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:157)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.internal.operations.DefaultBuildOperationExecutor.call(DefaultBuildOperationExecutor.java:73)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
	at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:74)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:402)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:389)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:382)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:368)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.lambda$run$0(DefaultPlanExecutor.java:127)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:191)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.executeNextNode(DefaultPlanExecutor.java:182)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:124)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
	at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:61)
Caused by: org.gradle.api.internal.provider.AbstractProperty$PropertyQueryException: Failed to calculate the value of task ':buildSrc:generateSourceRoots' property 'sourceRoots'.
	at org.gradle.api.internal.provider.AbstractProperty.finalizeNow(AbstractProperty.java:239)
	at org.gradle.api.internal.provider.AbstractProperty.beforeRead(AbstractProperty.java:230)
	at org.gradle.api.internal.provider.AbstractProperty.calculateOwnValue(AbstractProperty.java:126)
	at org.gradle.api.internal.provider.AbstractMinimalProvider.getOrNull(AbstractMinimalProvider.java:93)
	at org.gradle.api.internal.provider.ProviderResolutionStrategy$1.resolve(ProviderResolutionStrategy.java:27)
	at org.gradle.util.internal.DeferredUtil.unpack(DeferredUtil.java:59)
	at org.gradle.util.internal.DeferredUtil.unpackOrNull(DeferredUtil.java:49)
	at org.gradle.api.internal.tasks.properties.InputParameterUtils.prepareInputParameterValue(InputParameterUtils.java:39)
	at org.gradle.api.internal.tasks.properties.InputParameterUtils.prepareInputParameterValue(InputParameterUtils.java:31)
	... 67 more
Caused by: org.gradle.api.UnknownProjectException: Project with path ':build-logic-module' could not be found in project ':buildSrc'.
	at org.gradle.api.internal.project.DefaultProject.project(DefaultProject.java:654)
	at org.gradle.api.internal.project.DefaultProject.project(DefaultProject.java:647)
	at org.gradle.api.internal.project.DefaultProject.project(DefaultProject.java:151)
	at org.gradle.kotlin.dsl.tooling.builders.BuildSrcClassPathModeConfigurationAction$projectDependenciesSourceRootsFrom$1$1.invoke(BuildSrcClassPathModeConfigurationAction.kt:79)
	at org.gradle.kotlin.dsl.tooling.builders.BuildSrcClassPathModeConfigurationAction$projectDependenciesSourceRootsFrom$1$1.invoke(BuildSrcClassPathModeConfigurationAction.kt:38)
	at kotlin.sequences.TransformingSequence$iterator$1.next(Sequences.kt:210)
	at kotlin.sequences.FilteringSequence$iterator$1.calcNext(Sequences.kt:170)
	at kotlin.sequences.FilteringSequence$iterator$1.hasNext(Sequences.kt:194)
	at kotlin.sequences.FlatteningSequence$iterator$1.ensureItemIterator(Sequences.kt:311)
	at kotlin.sequences.FlatteningSequence$iterator$1.hasNext(Sequences.kt:303)
	at kotlin.sequences.TransformingSequence$iterator$1.hasNext(Sequences.kt:214)
	at kotlin.sequences.SequencesKt___SequencesKt.toCollection(_Sequences.kt:786)
	at kotlin.sequences.SequencesKt___SequencesKt.toMutableList(_Sequences.kt:816)
	at kotlin.sequences.SequencesKt___SequencesKt.toList(_Sequences.kt:807)
	at org.gradle.kotlin.dsl.tooling.builders.BuildSrcClassPathModeConfigurationAction$projectDependenciesSourceRootsFrom$1.call(BuildSrcClassPathModeConfigurationAction.kt:83)
	at org.gradle.kotlin.dsl.tooling.builders.BuildSrcClassPathModeConfigurationAction$projectDependenciesSourceRootsFrom$1.call(BuildSrcClassPathModeConfigurationAction.kt:38)
	at org.gradle.api.internal.provider.DefaultProvider.calculateOwnValue(DefaultProvider.java:66)
	at org.gradle.api.internal.provider.AbstractMinimalProvider.calculateValue(AbstractMinimalProvider.java:103)
	at org.gradle.api.internal.provider.Collectors$ElementsFromCollectionProvider.collectEntries(Collectors.java:216)
	at org.gradle.api.internal.provider.AbstractCollectionProperty$CollectingSupplier.calculateValue(AbstractCollectionProperty.java:337)
	at org.gradle.api.internal.provider.AbstractCollectionProperty.finalValue(AbstractCollectionProperty.java:189)
	at org.gradle.api.internal.provider.AbstractCollectionProperty.finalValue(AbstractCollectionProperty.java:37)
	at org.gradle.api.internal.provider.AbstractProperty.finalizeNow(AbstractProperty.java:236)
	... 75 more
```

## Contribution policy

Contributions via GitHub pull requests are gladly accepted from their original author. Along with any pull requests, please state that the contribution is your original work and that you license the work to the project under the project's open source license. Whether or not you state this explicitly, by submitting any copyrighted material via pull request, email, or other means you agree to license the material under the project's open source license and warrant that you have the legal authority to do so.

## License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
