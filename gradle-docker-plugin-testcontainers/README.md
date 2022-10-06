# gradle-docker-plugin-testcontainers

Reproducer project for https://github.com/bmuschko/gradle-docker-plugin/issues/954 - a classpath issue when trying to test `com.bmuschko:gradle-docker-plugin` using testcontainers.

Running `com.example.ExampleSpec` yields the following:

```Condition failed with Exception:
container.start()
|         |
|         java.lang.NullPointerException: Cannot invoke "java.lang.Long.longValue()" because the return value of "com.github.dockerjava.api.model.Info.getMemTotal()" is null
|         	at org.testcontainers.DockerClientFactory.client(DockerClientFactory.java:199)
|         	at org.testcontainers.DockerClientFactory$1.getDockerClient(DockerClientFactory.java:90)
|         	at com.github.dockerjava.api.DockerClientDelegate.authConfig(DockerClientDelegate.java:108)
|         	at org.testcontainers.containers.GenericContainer.start(GenericContainer.java:325)
|         	at com.example.ExampleSpec.test(ExampleSpec.groovy:13)
GenericContainer(exposedPorts=[], portBindings=[], extraHosts=[], networkMode=null, network=null, networkAliases=[tc-HVPfHmro], image=RemoteDockerImage(imageName=busybox:1.33.0, imagePullPolicy=DefaultPullPolicy(), imageNameSubstitutor=org.testcontainers.utility.ImageNameSubstitutor$LogWrappedImageNameSubstitutor@16bbdc4b), env=[], labels={}, commandParts=[], binds=[], privilegedMode=false, volumesFroms=[], linkedContainers={}, startupCheckStrategy=org.testcontainers.containers.startupcheck.IsRunningStartupCheckStrategy@798811f0, startupAttempts=1, workingDirectory=null, shmSize=null, copyToFileContainerPathMap={}, copyToTransferableContainerPathMap={}, dependencies=[], dockerClient=LazyDockerClient, containerId=null, containerInfo=null, waitStrategy=org.testcontainers.containers.wait.strategy.HostPortWaitStrategy@3119c30e, logConsumers=[], createContainerCmdModifiers=[], tmpFsMapping=null, shouldBeReused=false, hostAccessible=false)
```
