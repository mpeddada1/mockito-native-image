# mockito-native-image


To run `child-module-1`:
- `cd child-module-1` 
- `mvn package -Pnative`
- `./child-module-1`

To run `child-module-2`:
- `cd child-module-2` 
- `mvn test -Pnative`


Without the configurations provided, we get:

```
Failures (1):
  JUnit Vintage:MySampleMockitoTest:testNativeImage
    MethodSource [className = 'com.example.MySampleMockitoTest', methodName = 'testNativeImage', methodParameterTypes = '']
    => com.oracle.svm.core.jdk.UnsupportedFeatureError: Proxy class defined by interfaces [interface org.mockito.plugins.PluginSwitch] not found. Generating proxy classes at runtime is not supported. Proxy classes need to be defined at image build time by specifying the list of interfaces that they implement. To define proxy classes use -H:DynamicProxyConfigurationFiles=<comma-separated-config-files> and -H:DynamicProxyConfigurationResources=<comma-separated-config-resources> options.
       org.graalvm.nativeimage.builder/com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:89)
       org.graalvm.nativeimage.builder/com.oracle.svm.core.reflect.proxy.DynamicProxySupport.getProxyClass(DynamicProxySupport.java:171)
       java.base@17.0.5/java.lang.reflect.Proxy.getProxyConstructor(Proxy.java:47)
       java.base@17.0.5/java.lang.reflect.Proxy.newProxyInstance(Proxy.java:1037)
       org.mockito.internal.configuration.plugins.PluginLoader.loadPlugin(PluginLoader.java:77)
       org.mockito.internal.configuration.plugins.PluginLoader.loadPlugin(PluginLoader.java:50)
       org.mockito.internal.configuration.plugins.PluginRegistry.<init>(PluginRegistry.java:21)
       org.mockito.internal.configuration.plugins.Plugins.<clinit>(Plugins.java:22)
       org.mockito.internal.MockitoCore.<clinit>(MockitoCore.java:73)
       org.mockito.Mockito.<clinit>(Mockito.java:1669)
       [...]
```

The json configuration files were generated using the trace-agent, however, all of the junit reflection configs had to be removed as they were resulting in the following error:
```
Fatal error: com.oracle.graal.pointsto.util.AnalysisError$ParsingError: Error encountered while parsing org.junit.platform.launcher.core.LauncherFactory.openSession(org.junit.platform.launcher.core.LauncherConfig) 
Parsing context:
   at org.junit.platform.launcher.core.LauncherFactory.openSession(LauncherFactory.java:98)
   at org.junit.platform.launcher.core.LauncherFactory.openSession(LauncherFactory.java:82)

	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisError.parsingError(AnalysisError.java:153)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.createFlowsGraph(MethodTypeFlow.java:104)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureFlowsGraphCreated(MethodTypeFlow.java:83)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.getOrCreateMethodFlowsGraph(MethodTypeFlow.java:65)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.typestate.DefaultStaticInvokeTypeFlow.update(DefaultStaticInvokeTypeFlow.java:64)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.PointsToAnalysis$1.run(PointsToAnalysis.java:488)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.executeCommand(CompletionExecutor.java:193)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.lambda$executeService$0(CompletionExecutor.java:177)
	at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1395)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1182)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1655)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1622)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:165)
Caused by: org.graalvm.compiler.java.BytecodeParser$BytecodeParserError: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: No instances of org.junit.platform.launcher.core.ServiceLoaderRegistry are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use --trace-object-instantiation=org.junit.platform.launcher.core.ServiceLoaderRegistry.
	at parsing org.junit.platform.launcher.core.LauncherFactory.createLauncherSessionListener(LauncherFactory.java:154)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.throwParserError(BytecodeParser.java:2518)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.throwParserError(SharedGraphBuilderPhase.java:110)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3393)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.handleBytecodeBlock(BytecodeParser.java:3345)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:3190)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.build(BytecodeParser.java:1138)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.buildRootMethod(BytecodeParser.java:1030)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.GraphBuilderPhase$Instance.run(GraphBuilderPhase.java:97)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.phases.SharedGraphBuilderPhase.run(SharedGraphBuilderPhase.java:84)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.run(Phase.java:49)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.BasePhase.apply(BasePhase.java:446)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:42)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:38)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.AnalysisParsedGraph.parseBytecode(AnalysisParsedGraph.java:135)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisMethod.ensureGraphParsed(AnalysisMethod.java:685)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.phases.InlineBeforeAnalysisGraphDecoder.lookupEncodedGraph(InlineBeforeAnalysis.java:180)
	at jdk.internal.vm.compiler/org.graalvm.compiler.replacements.PEGraphDecoder.doInline(PEGraphDecoder.java:1162)
	at jdk.internal.vm.compiler/org.graalvm.compiler.replacements.PEGraphDecoder.tryInline(PEGraphDecoder.java:1145)
	at jdk.internal.vm.compiler/org.graalvm.compiler.replacements.PEGraphDecoder.trySimplifyInvoke(PEGraphDecoder.java:1003)
	at jdk.internal.vm.compiler/org.graalvm.compiler.replacements.PEGraphDecoder.handleInvoke(PEGraphDecoder.java:957)
	at jdk.internal.vm.compiler/org.graalvm.compiler.nodes.GraphDecoder.processNextNode(GraphDecoder.java:817)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.phases.InlineBeforeAnalysisGraphDecoder.processNextNode(InlineBeforeAnalysis.java:240)
	at jdk.internal.vm.compiler/org.graalvm.compiler.nodes.GraphDecoder.decode(GraphDecoder.java:548)
	at jdk.internal.vm.compiler/org.graalvm.compiler.replacements.PEGraphDecoder.decode(PEGraphDecoder.java:833)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.phases.InlineBeforeAnalysis.decodeGraph(InlineBeforeAnalysis.java:98)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:179)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:349)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.flow.MethodTypeFlow.createFlowsGraph(MethodTypeFlow.java:93)
	... 12 more
Caused by: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: No instances of org.junit.platform.launcher.core.ServiceLoaderRegistry are allowed in the image heap as this class should be initialized at image runtime. To see how this object got instantiated use --trace-object-instantiation=org.junit.platform.launcher.core.ServiceLoaderRegistry.

```
