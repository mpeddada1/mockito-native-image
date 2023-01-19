# mockito-native-image

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
