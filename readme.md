### RenderNurse
> A simple bootstrap utility to start renderdoc in a java application.
---
### Usage
Add the published artifact:
```
net.neoforged:RenderNurse:0.0.4
```
or any other version you want to use to your runtime classpath.
It has no compile dependency and no API other than the system properties that should be set for startup.

#### Environment variables
On linux it is required to start your JVM process with the following environment variables:
```
export LD_PRELOAD=/path/to/renderdoc/lib
```
This ensures that the renderdoc library is loaded before the JVM starts.
You can set this environment variable on Windows as well, but it won't be used by the JVM.

#### System properties
The following system properties need be set to configure the renderdoc library:
```
-Dneoforge.rendernurse.renderdoc.library=/path/to/renderdoc/lib
```
This is the path to the renderdoc library that should be loaded by the JVM.

#### JVM arguments
Besides the system properties, you can also set the following JVM arguments:
```
-javaagent:/path/to/RenderNurse.jar
--enable-preview
--enable-native-access=ALL-UNNAMED
```

#### MacOS
Due to RenderDoc not being supported on MacOS, this utility will not work on MacOS.

### Exit codes
The utility will exit with the following exit codes, any exit code indicates a failure:
- 100: RenderDoc library not found
- 101: RenderDoc library is not a file
- 102: RenderDoc library could not be loaded by the JVM
- 120: Tried to start RenderDoc on MacOS
- 140: LD_PRELOAD environment variable not set on Linux
- 150: RenderDoc version is not compatible with the 1.6 API of RenderDoc
- 151: RenderDoc could not be started
