# buildSrc-included-build-dependency

Sample for showing problems with Intellij sync when buildSrc depends on a module from an included build.

## Gradle nightly & IntelliJ 2021.3 RC3

When using Gradle latest nightly which has a fix for https://github.com/gradle/gradle/issues/18984 and IntelliJ 2021.3 RC3
the project import fails with `Collection contains no element matching the predicate.`

The idea.log file contains the following:

```
2021-11-24 11:01:33,340 [      0]   INFO -        #com.intellij.idea.Main - ------------------------------------------------------ IDE STARTED ------------------------------------------------------ 
2021-11-24 11:01:33,353 [     13]   INFO - .intellij.util.EnvironmentUtil - loading shell env: /usr/local/bin/fish -l -c '/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/bin/printenv' '/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/intellij-shell-env.9436109268928506199.tmp' 
2021-11-24 11:01:33,391 [     51]   INFO -        #com.intellij.idea.Main - IDE: IntelliJ IDEA (build #IC-213.5744.125, 17 Nov 2021 16:47) 
2021-11-24 11:01:33,392 [     52]   INFO -        #com.intellij.idea.Main - OS: Mac OS X (11.6, x86_64) 
2021-11-24 11:01:33,402 [     62]   INFO -        #com.intellij.idea.Main - JRE: 11.0.13+7-b1751.19 (JetBrains s.r.o.) 
2021-11-24 11:01:33,403 [     63]   INFO -        #com.intellij.idea.Main - JVM: 11.0.13+7-b1751.19 (OpenJDK 64-Bit Server VM) 
2021-11-24 11:01:33,407 [     67]   INFO -        #com.intellij.idea.Main - JVM options: [-Xms128m, -Xmx750m, -XX:ReservedCodeCacheSize=512m, -XX:+IgnoreUnrecognizedVMOptions, -XX:SoftRefLRUPolicyMSPerMB=50, -XX:CICompilerCount=2, -XX:+HeapDumpOnOutOfMemoryError, -XX:-OmitStackTraceInFastThrow, -ea, -Dsun.io.useCanonCaches=false, -Djdk.http.auth.tunneling.disabledSchemes="", -Djdk.attach.allowAttachSelf=true, -Djdk.module.illegalAccess.silent=true, -Dkotlinx.coroutines.debug=off, -XX:ErrorFile=/Users/bene/java_error_in_idea_%p.log, -XX:HeapDumpPath=/Users/bene/java_error_in_idea.hprof, -Xms128m, -Xmx750m, -XX:ReservedCodeCacheSize=512m, -XX:+IgnoreUnrecognizedVMOptions, -XX:+UseG1GC, -XX:SoftRefLRUPolicyMSPerMB=50, -XX:CICompilerCount=2, -XX:+HeapDumpOnOutOfMemoryError, -XX:-OmitStackTraceInFastThrow, -ea, -Dsun.io.useCanonCaches=false, -Djdk.http.auth.tunneling.disabledSchemes="", -Djdk.attach.allowAttachSelf=true, -Djdk.module.illegalAccess.silent=true, -Dkotlinx.coroutines.debug=off, -XX:ErrorFile=/Users/bene/java_error_in_idea_%p.log, -XX:HeapDumpPath=/Users/bene/java_error_in_idea.hprof, -Dide.no.platform.update=true, -Dtoolbox.notification.token=uQ10ayZ7AtwXGRADbxscnV6ohLLfqTbY9qV-pqCqWvU=, -Dtoolbox.notification.portFile=/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app.vmoptions.port, -Didea.plugins.path=/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app.plugins, -Djb.vmOptionsFile=/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app.vmoptions, -Dsplash=true, -Didea.home.path=/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents, -Didea.jre.check=true, -Didea.executable=idea, -Djava.system.class.loader=com.intellij.util.lang.PathClassLoader, -Didea.platform.prefix=Idea, -Didea.paths.selector=IdeaIC2021.3, -Didea.vendor.name=JetBrains] 
2021-11-24 11:01:33,407 [     67]   INFO -        #com.intellij.idea.Main - args: [/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency] 
2021-11-24 11:01:33,408 [     68]   INFO -        #com.intellij.idea.Main - library path: /Users/bene/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:. 
2021-11-24 11:01:33,408 [     68]   INFO -        #com.intellij.idea.Main - boot library path: /Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/jbr/Contents/Home/lib 
2021-11-24 11:01:33,441 [    101]   INFO -        #com.intellij.idea.Main - locale=de_DE JNU=UTF-8 file.encoding=UTF-8
  idea.config.path=/Users/bene/Library/Application Support/JetBrains/IdeaIC2021.3
  idea.system.path=/Users/bene/Library/Caches/JetBrains/IdeaIC2021.3
  idea.plugins.path=/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app.plugins
  idea.log.path=/Users/bene/Library/Logs/JetBrains/IdeaIC2021.3 
2021-11-24 11:01:33,453 [    113]   INFO -        #com.intellij.idea.Main - CPU cores: 12; ForkJoinPool.commonPool: java.util.concurrent.ForkJoinPool@65e8da40[Running, parallelism = 11, size = 9, active = 6, running = 5, steals = 1, tasks = 0, submissions = 0]; factory: com.intellij.concurrency.IdeaForkJoinWorkerThreadFactory@d41c99c 
2021-11-24 11:01:33,559 [    219]   INFO - llij.ide.plugins.PluginManager - Plugin PluginDescriptor(name=Groovy, id=org.intellij.groovy, descriptorPath=plugin.xml, path=~/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/Groovy, version=213.5744.125, package=org.jetbrains.plugins.groovy, isBundled=true) misses optional descriptor duplicates-groovy.xml 
2021-11-24 11:01:33,560 [    220]   INFO - llij.ide.plugins.PluginManager - Plugin PluginDescriptor(name=Groovy, id=org.intellij.groovy, descriptorPath=plugin.xml, path=~/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/Groovy, version=213.5744.125, package=org.jetbrains.plugins.groovy, isBundled=true) misses optional descriptor duplicates-detection-groovy.xml 
2021-11-24 11:01:33,600 [    260]   INFO -        #com.intellij.idea.Main - JNA library (64-bit) loaded in 146 ms 
2021-11-24 11:01:33,655 [    315]   INFO - .intellij.util.EnvironmentUtil - shell environment loaded (36 vars) 
2021-11-24 11:01:33,789 [    449]   INFO - llij.ide.plugins.PluginManager - Module intellij.space.index is not enabled because dependency intellij.indexing.shared is not available 
2021-11-24 11:01:33,820 [    480]   INFO - llij.ide.plugins.PluginManager - Loaded bundled plugins: IDEA CORE (213.5744.125), IDE Features Trainer (213.5744.125), Grazie (213.5744.125), Settings Repository (213.5744.125), YAML (213.5744.125), WSL File System Support (213.5744.125), TextMate Bundles (213.5744.125), Terminal (213.5744.125), JetBrains maven model api classes (213.5744.125), JetBrains Repository Search (213.5744.125), Qodana (213.5744.125), EditorConfig (213.5744.125), WebP Support (213.5744.125), Mercurial (213.5744.125), Shell Script (213.5744.125), Projector Libraries for Code With Me and Remote Development (213.5744.125), JetBrains Marketplace Licensing Support (213.5744.125), macOS Light Theme (213.5744.125), Dependency Management Api for External Build Tools (213.5744.125), Copyright (213.5744.125), Properties (213.5744.125), Gradle (213.5744.125), Images (213.5744.125), Java (213.5744.125), JavaFX (213.5744.125), Kotlin (213-1.5.10-release-949-IJ5744.125), Java Bytecode Decompiler (213.5744.125), Eclipse Interoperability (213.5744.125), Java Stream Debugger (213.5744.125), Package Search (213.5744.125), com.intellij.tracing.ide (213.5744.125), Java IDE Customization (213.5744.125), Java Internationalization (213.5744.125), UI Designer (213.5744.125), Configuration Script (213.5744.125), Shared Indexes (213.5744.125), Machine Learning Code Completion (213.5744.125), Smali Support (213.5744.125), XPathView + XSLT (213.5744.125), IntelliLang (213.5744.125), Markdown (213.5744.125), Task Management (213.5744.125), XSLT Debugger (213.5744.125), TestNG (213.5744.125), Subversion (213.5744.125), Perforce Helix Core (213.5744.125), Lombok (213.5744.125), JUnit (213.5744.125), GitHub (213.5744.125), Space (213.5744.125), Git (213.5744.125), IDE Features Trainer: Git Lessons (213.5744.125), ChangeReminder (213.5744.125), Machine Learning in Search Everywhere (213.5744.125), Code Coverage for Java (213.5744.125), Bytecode Viewer (213.5744.125), Groovy (213.5744.125), Ant (213.5744.125), Maven (213.5744.125), Gradle-Java (213.5744.125), Gradle-Maven (213.5744.125), Gradle DSL API (213.5744.125), Gradle Dependency Updater Implementation (213.5744.125), Android (2020.3.1 Final.213.5744.125), Plugin DevKit (213.5744.125) 
2021-11-24 11:01:33,820 [    480]   INFO - llij.ide.plugins.PluginManager - Disabled plugins: Code With Me (213.5744.125) 
2021-11-24 11:01:34,509 [   1169]   INFO - ication.options.PathMacrosImpl - Loaded path macros: {MAVEN_REPOSITORY=/Users/bene/.m2/repository, KOTLIN_BUNDLED=/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/Kotlin/kotlinc} 
2021-11-24 11:01:34,548 [   1208]   INFO - rains.ide.BuiltInServerManager - built-in server started, port 63343 
2021-11-24 11:01:34,551 [   1211]   INFO - cation.options.RegistryManager - Registry values changed by user: scala.erase.compiler.process.jdk.once = false 
2021-11-24 11:01:34,620 [   1280]   INFO - lij.diagnostic.DebugLogManager - Set TRACE for the following categories: #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions, #com.jetbrains.rdserver.requests, #com.jetbrains.rd.ide.document, #com.jetbrains.rdserver.document, #com.jetbrains.rdserver.editors, #com.jetbrains.rdserver.actions 
2021-11-24 11:01:34,636 [   1296]   INFO - intellij.util.io.FilePageCache - lower=100; upper=500; buffer=10; max=730 
2021-11-24 11:01:34,642 [   1302]   INFO - til.net.ssl.CertificateManager - Default SSL context initialized 
2021-11-24 11:01:34,666 [   1326]   INFO - til.io.storage.AbstractStorage - Space waste in /Users/bene/Library/Caches/JetBrains/IdeaIC2021.3/caches/attrib.dat is 6841616 bytes. Compacting now. 
2021-11-24 11:01:34,670 [   1330]   INFO - intellij.diagnostic.JitWatcher - JIT compilation state checking enabled 
2021-11-24 11:01:35,290 [   1950]   INFO - til.io.storage.AbstractStorage - Done compacting in 624msec. 
2021-11-24 11:01:35,528 [   2188]   INFO - ntellij.idea.ApplicationLoader - ApplicationLoader.loadProject (cwd=null) 
2021-11-24 11:01:35,529 [   2189]   INFO - ellij.ide.CommandLineProcessor - External command line:
Dir: null
/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency
----- 
2021-11-24 11:01:35,626 [   2286]   INFO - ntellij.ui.mac.screenmenu.Menu - new screen menu: AquaMenuBarUI.disableJBScreenMenuBar not found, can't use new menu impl, disableJbScreenMenuBar 
2021-11-24 11:01:35,626 [   2286]   INFO - ntellij.ui.mac.screenmenu.Menu - new screen menu: disableJbScreenMenu isn't supported by runtime, new screen menu is disabled 
2021-11-24 11:01:35,635 [   2295]   INFO - BridgeArtifactManagerRedefiner - Using workspace model to open project 
2021-11-24 11:01:35,743 [   2403]   INFO - pl.local.NativeFileWatcherImpl - Starting file watcher: /Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/bin/fsnotifier 
2021-11-24 11:01:35,764 [   2424]   INFO - pl.local.NativeFileWatcherImpl - Native file watcher is operational. 
2021-11-24 11:01:35,783 [   2443]   INFO - penapi.application.Experiments - Experimental features enabled for user: wsl.p9.support, wsl.prefer.p9.support, wsl.p9.show.roots.in.file.chooser, wsl.execute.with.wsl.exe, linux.native.menu, recent.and.edited.files.together, show.create.new.element.in.popup, search.everywhere.mixed.results, editor.reader.mode, new.large.text.file.viewer, wsl.fsd.content.loader, terminal.shell.command.handling 
2021-11-24 11:01:35,790 [   2450]   INFO - pl.projectlevelman.NewMappings - VCS Root: [Git] - [/Users/bene/workspace/gradle/sample-projects/gradle-sandbox] 
2021-11-24 11:01:35,821 [   2481]   INFO - impl.SharedIndexMainZipStorage - Shared Indexes Storage is opened: 1 chunk(s), 46,08 MB, chunks: [11.0.13-corretto-11.0.13-43bb9bfc413f1497b4d2447a8a101299c6af2dd6f66ff76b4340702f945a3373-6743042237da] 
2021-11-24 11:01:35,861 [   2521]   INFO - .intellij.openapi.wm.StatusBar - Factory is not registered as `com.intellij.statusBarWidgetFactory` extension: com.intellij.openapi.vcs.changes.committed.IncomingChangesIndicatorFactory 
2021-11-24 11:01:35,900 [   2560]   INFO - rojectCodeStyleSettingsManager - Loading Project code style 
2021-11-24 11:01:35,911 [   2571]   INFO - eStyle.CustomCodeStyleSettings - Loaded org.jetbrains.plugins.groovy.codeStyle.GroovyCodeStyleSettings 
2021-11-24 11:01:35,912 [   2572]   INFO - eStyle.CustomCodeStyleSettings - Loaded org.jetbrains.kotlin.idea.core.formatter.KotlinCodeStyleSettings 
2021-11-24 11:01:35,915 [   2575]   INFO - eStyle.CustomCodeStyleSettings - Loaded org.jetbrains.kotlin.idea.core.formatter.KotlinCodeStyleSettings 
2021-11-24 11:01:35,919 [   2579]   INFO - eStyle.CommonCodeStyleSettings - Loaded Kotlin common code style settings 
2021-11-24 11:01:35,919 [   2579]   INFO - eStyle.CommonCodeStyleSettings - Loaded Kotlin common code style settings 
2021-11-24 11:01:35,920 [   2580]   INFO - rojectCodeStyleSettingsManager - Project code style loaded 
2021-11-24 11:01:35,926 [   2586]   INFO - eStyle.CustomCodeStyleSettings - Loaded org.jetbrains.plugins.groovy.codeStyle.GroovyCodeStyleSettings 
2021-11-24 11:01:35,927 [   2587]   INFO - eStyle.CustomCodeStyleSettings - Loaded org.jetbrains.kotlin.idea.core.formatter.KotlinCodeStyleSettings 
2021-11-24 11:01:35,927 [   2587]   INFO - eStyle.CustomCodeStyleSettings - Loaded org.jetbrains.kotlin.idea.core.formatter.KotlinCodeStyleSettings 
2021-11-24 11:01:35,927 [   2587]   INFO - eStyle.CommonCodeStyleSettings - Loaded Kotlin common code style settings 
2021-11-24 11:01:35,927 [   2587]   INFO - eStyle.CommonCodeStyleSettings - Loaded Kotlin common code style settings 
2021-11-24 11:01:36,074 [   2734]   INFO - el.ide.impl.WorkspaceModelImpl - Load workspace model from cache in 362 ms 
2021-11-24 11:01:36,579 [   3239]   INFO - al.NewToolbarRootPaneExtension - Show old main toolbar: false; show old navigation bar: true 
2021-11-24 11:01:36,975 [   3635]   INFO - leBasedIndexDataInitialization - Index data initialization done: 1460 ms. Initialized indexes: [FilenameIndex, FrameworkDetectionIndex, TodoIndex, IdIndex, filetypes, Stubs, Trigram.Index, fileIncludes, DomFileIndex, RelaxSymbolIndex, XmlTagNames, XmlNamespaces, html5.custom.attributes.index, SchemaTypeInheritance, json.file.root.values, yaml.keys.name, editorconfig.index.name, xmlProperties, bytecodeAnalysis, java.auto.module.name, java.source.module.name, java.null.method.argument, java.fun.expression, java.binary.plus.expression, JavaFxControllerClassIndex, javafx.id.name, javafx.custom.component, org.jetbrains.kotlin.idea.versions.KotlinJvmMetadataVersionIndex, org.jetbrains.kotlin.idea.versions.KotlinJsMetadataVersionIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinClassFileIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinJavaScriptMetaFileIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinMetadataFileIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinMetadataFilePackageIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinModuleMappingIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinPackageSourcesMemberNamesIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinJvmModuleAnnotationsIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinBuiltInsMetadataIndex, org.jetbrains.kotlin.idea.vfilefinder.KotlinStdlibIndex, org.jetbrains.kotlin.idea.vfilefinder.KlibMetaFileIndex, com.intellij.uiDesigner.FormClassIndex, XsltSymbolIndex, groovy.trait.fields, groovy.trait.methods, LombokConfigIndex, ant-imports, com.android.tools.idea.model.AndroidManifestIndex$Companion$NAME$1.NAME, BindingXmlIndex, MlModelFileIndex, NavXmlIndex, IdeaPluginRegistrationIndex, PluginIdModuleIndex, PluginIdDependenciesIndex, devkit.ExtensionPointIndex, devkit.ExtensionPointClassIndex]. 
2021-11-24 11:01:37,084 [   3744]   INFO - exImpl$StubIndexInitialization - Index data initialization done: 108 ms. Initialized stub indexes: {kotlin.primeIndexKey, org.jetbrains.kotlin.idea.stubindex.KotlinSubclassObjectNameIndex, jvm.static.member.type, dom.namespaceKey, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelPropertyFqnNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelTypeAliasFqNameIndex, gr.class.super, org.jetbrains.kotlin.idea.stubindex.KotlinClassShortNameIndex, gr.annot.members, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelTypeAliasByPackageIndex, java.anonymous.baseref, dom.elementClass, jvm.static.member.name, properties.index, java.method.parameter.types, java.class.extlist, org.jetbrains.kotlin.idea.stubindex.KotlinFunctionShortNameIndex, java.class.fqn, org.jetbrains.kotlin.idea.stubindex.KotlinAnnotationsIndex, java.module.name, gr.method.name, gr.field.name, gr.script.fqn, org.jetbrains.kotlin.idea.stubindex.KotlinTypeAliasShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinPropertyShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinExactPackagesIndex, java.annotations, java.class.shortname, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelFunctionByPackageIndex, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelClassByPackageIndex, java.field.name, java.method.name, gr.anonymous.class, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelPropertyByPackageIndex, gr.annot.method.name, org.jetbrains.kotlin.idea.stubindex.KotlinFullClassNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinTypeAliasByExpansionShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinSuperClassIndex, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelExtensionsByReceiverTypeIndex, markdown.header, gr.class.fqn, org.jetbrains.kotlin.idea.stubindex.KotlinTopLevelFunctionFqnNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinProbablyNothingPropertyShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinProbablyNothingFunctionShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinProbablyContractedFunctionShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinJvmNameAnnotationIndex, org.jetbrains.kotlin.idea.stubindex.KotlinExtensionsInObjectsByReceiverTypeIndex, org.jetbrains.kotlin.idea.stubindex.KotlinOverridableInternalMembersShortNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinScriptFqnIndex, org.jetbrains.kotlin.idea.stubindex.KotlinFileFacadeFqNameIndex, org.jetbrains.kotlin.idea.stubindex.KotlinFilePartClassIndex, org.jetbrains.kotlin.idea.stubindex.KotlinFileFacadeClassByPackageIndex, org.jetbrains.kotlin.idea.stubindex.KotlinMultifileClassPartIndex, org.jetbrains.kotlin.idea.stubindex.KotlinFileFacadeShortNameIndex, gr.script.class}. 
2021-11-24 11:01:38,991 [   5651]   INFO -                         STDOUT - Initialize stderr logger, severity=LOGSEVERITY_DISABLE 
2021-11-24 11:01:39,759 [   6419]   INFO - ings.impl.UpdateCheckerService - channel: release 
2021-11-24 11:01:39,817 [   6477]   INFO - m.intellij.ui.mac.touchbar.NST - nst library works properly, successfully created and released native touchbar object 
2021-11-24 11:01:39,819 [   6479]   INFO - i.mac.touchbar.TouchbarSupport - touchbar support is enabled 
2021-11-24 11:01:40,591 [   7251]   INFO - til.indexing.RootChangesLogger - New rootsChanged event for "buildSrc-included-build-dependency" project with full reindex with trace_hash = 1277961680:
java.lang.Throwable
	at com.intellij.util.indexing.RootChangesLogger.info(RootChangesLogger.java:27)
	at com.intellij.util.indexing.EntityIndexingServiceImpl.logRootChanges(EntityIndexingServiceImpl.java:99)
	at com.intellij.util.indexing.EntityIndexingServiceImpl.runFullReindex(EntityIndexingServiceImpl.java:84)
	at com.intellij.util.indexing.EntityIndexingServiceImpl.indexChanges(EntityIndexingServiceImpl.java:46)
	at com.intellij.openapi.roots.impl.ProjectRootManagerComponent.synchronizeRoots(ProjectRootManagerComponent.java:298)
	at com.intellij.openapi.roots.impl.ProjectRootManagerComponent.fireRootsChangedEvent(ProjectRootManagerComponent.java:211)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.fireRootsChanged(ProjectRootManagerImpl.java:447)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$1.fireRootsChanged(ProjectRootManagerImpl.java:145)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$1.fireRootsChanged(ProjectRootManagerImpl.java:142)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$BatchSession.lambda$levelDown$0(ProjectRootManagerImpl.java:90)
	at com.intellij.openapi.application.WriteAction.lambda$run$1(WriteAction.java:86)
	at com.intellij.openapi.application.impl.ApplicationImpl.runWriteActionWithClass(ApplicationImpl.java:935)
	at com.intellij.openapi.application.impl.ApplicationImpl.runWriteAction(ApplicationImpl.java:961)
	at com.intellij.openapi.application.WriteAction.run(WriteAction.java:85)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$BatchSession.levelDown(ProjectRootManagerImpl.java:90)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.mergeRootsChangesDuring(ProjectRootManagerImpl.java:393)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.projectJdkChanged(ProjectRootManagerImpl.java:318)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.lambda$loadState$1(ProjectRootManagerImpl.java:359)
	at com.intellij.openapi.application.impl.ApplicationImpl.runWriteAction(ApplicationImpl.java:947)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.lambda$loadState$2(ProjectRootManagerImpl.java:359)
	at com.intellij.openapi.application.TransactionGuardImpl.runWithWritingAllowed(TransactionGuardImpl.java:214)
	at com.intellij.openapi.application.TransactionGuardImpl.access$200(TransactionGuardImpl.java:21)
	at com.intellij.openapi.application.TransactionGuardImpl$2.run(TransactionGuardImpl.java:196)
	at com.intellij.openapi.application.impl.ApplicationImpl.runIntendedWriteActionOnCurrentThread(ApplicationImpl.java:805)
	at com.intellij.openapi.application.impl.ApplicationImpl.lambda$invokeLater$4(ApplicationImpl.java:348)
	at com.intellij.openapi.application.impl.FlushQueue.doRun(FlushQueue.java:82)
	at com.intellij.openapi.application.impl.FlushQueue.runNextEvent(FlushQueue.java:131)
	at com.intellij.openapi.application.impl.FlushQueue.flushNow(FlushQueue.java:47)
	at com.intellij.openapi.application.impl.FlushQueue$FlushNow.run(FlushQueue.java:187)
	at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:313)
	at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:776)
	at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:727)
	at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:721)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:85)
	at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:746)
	at com.intellij.ide.IdeEventQueue.defaultDispatchEvent(IdeEventQueue.java:891)
	at com.intellij.ide.IdeEventQueue._dispatchEvent(IdeEventQueue.java:760)
	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$6(IdeEventQueue.java:447)
	at com.intellij.openapi.progress.impl.CoreProgressManager.computePrioritized(CoreProgressManager.java:818)
	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$7(IdeEventQueue.java:446)
	at com.intellij.openapi.application.impl.ApplicationImpl.runIntendedWriteActionOnCurrentThread(ApplicationImpl.java:805)
	at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:492)
	at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:203)
	at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:124)
	at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:113)
	at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
	at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
	at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
 
2021-11-24 11:01:40,729 [   7389]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,729 [   7389]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,734 [   7394]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,735 [   7395]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,739 [   7399]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,739 [   7399]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,739 [   7399]   INFO - ion.DelayedProjectSynchronizer - Workspace model loaded from cache. Syncing real project state into workspace model in 975 ms. Thread[JobScheduler FJ pool 10/11,4,main] 
2021-11-24 11:01:40,741 [   7401]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,741 [   7401]   INFO - org.jetbrains.io.BuiltInServer - Channel will be closed due to error 
2021-11-24 11:01:40,842 [   7502]   INFO -  #git4idea.commands.GitHandler - [.] git version 
2021-11-24 11:01:40,868 [   7528]   INFO -  #git4idea.commands.GitHandler - git version 2.33.1 
2021-11-24 11:01:40,888 [   7548]   INFO - ea.config.GitExecutableManager - Git version for /usr/local/bin/git: 2.33.1.0 (UNIX) 
2021-11-24 11:01:40,894 [   7554]   INFO - ij.psi.search.LogFileTypeIndex - Loading file type index snapshot 
2021-11-24 11:01:40,943 [   7603]   INFO - ge.ExternalProjectsDataStorage - Load external projects data in 76 millis (read time: 74) 
2021-11-24 11:01:40,965 [   7625]   INFO - indexing.UnindexedFilesUpdater - Started indexing of buildSrc-included-build-dependency. Reason: On project open 
2021-11-24 11:01:40,978 [   7638]   INFO - indexing.UnindexedFilesUpdater - Performing delayed pushing properties tasks for buildSrc-included-build-dependency took 1ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:41,046 [   7706]   INFO - indexing.UnindexedFilesUpdater - Scanning of buildSrc-included-build-dependency uses 11 scanning threads 
2021-11-24 11:01:41,199 [   7859]   INFO - ing.roots.GradleBuildRootIndex - /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency: null -> org.jetbrains.kotlin.idea.gradleJava.scripting.roots.Imported@794cf3e0 
2021-11-24 11:01:41,254 [   7914]   INFO - s.plugins.gradle.GradleManager - Instructing gradle to use java from /Users/bene/.sdkman/candidates/java/11.0.10.hs-adpt 
2021-11-24 11:01:41,259 [   7919]   INFO - g.jetbrains.kotlin.idea.script - [KOTLIN_SCRIPTING] Loading script definitions [org.gradle.kotlin.dsl.KotlinInitScript] using classpath: /Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-core-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-core-api-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-kotlin-dsl-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-kotlin-dsl-tooling-models-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/kotlin-stdlib-1.5.31.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/kotlin-compiler-embeddable-1.5.31-patched-for-gradle-7.4.jar 
2021-11-24 11:01:41,419 [   8079]   INFO - redIndexChunkConfigurationImpl - Chunk 11.0.13-corretto-11.0.13-43bb9bfc413f1497b4d2447a8a101299c6af2dd6f66ff76b4340702f945a3373-6743042237da is registered for project 'buildSrc-included-build-dependency: matching: (fb=50, stub=55), incompatible: (fb=0, stub=0), unknown: (fb=10, stub=25),  
2021-11-24 11:01:41,550 [   8210]   INFO - s.plugins.gradle.GradleManager - Instructing gradle to use java from /Users/bene/.sdkman/candidates/java/11.0.10.hs-adpt 
2021-11-24 11:01:41,550 [   8210]   INFO - g.jetbrains.kotlin.idea.script - [KOTLIN_SCRIPTING] Loading script definitions [org.gradle.kotlin.dsl.KotlinSettingsScript] using classpath: /Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-core-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-core-api-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-kotlin-dsl-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-kotlin-dsl-tooling-models-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/kotlin-stdlib-1.5.31.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/kotlin-compiler-embeddable-1.5.31-patched-for-gradle-7.4.jar 
2021-11-24 11:01:41,575 [   8235]   INFO - s.plugins.gradle.GradleManager - Instructing gradle to use java from /Users/bene/.sdkman/candidates/java/11.0.10.hs-adpt 
2021-11-24 11:01:41,576 [   8236]   INFO - g.jetbrains.kotlin.idea.script - [KOTLIN_SCRIPTING] Loading script definitions [org.gradle.kotlin.dsl.KotlinBuildScript] using classpath: /Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-core-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-core-api-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-kotlin-dsl-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/gradle-kotlin-dsl-tooling-models-7.4.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/kotlin-stdlib-1.5.31.jar:/Users/bene/.gradle/wrapper/dists/gradle-7.4-20211122230135+0000-bin/dj1jsq6cowzdq3hxjglz72pq1/gradle-7.4-20211122230135+0000/lib/kotlin-compiler-embeddable-1.5.31-patched-for-gradle-7.4.jar 
2021-11-24 11:01:41,691 [   8351]   INFO - oad.SharedIndexDownloadService - There is no need to download shared indexes for JdkSharedIndexSuggestion(SharedIndexId(kind=jdk, url=https://index-cdn.jetbrains.com/v2/jdk, indexId=c953d5afd8ac8f025fd907719ba5e02098f0e7b5e3cf135b080bd1ac3cece206#version 11.0.10), lazy). Similar compatible shared indexes are available locally. 
2021-11-24 11:01:41,696 [   8356]   WARN - a.repo.GitUntrackedFilesHolder - Ignoring ignored file under another root: /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/.DS_Store; root: /Users/bene/workspace/gradle/sample-projects/gradle-sandbox; mapped root: null 
2021-11-24 11:01:41,699 [   8359]   WARN - a.repo.GitUntrackedFilesHolder - Ignoring ignored file under another root: /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/groovy-static-methods/app/src/main/groovy/com/.DS_Store; root: /Users/bene/workspace/gradle/sample-projects/gradle-sandbox; mapped root: null 
2021-11-24 11:01:42,072 [   8732]   WARN - ctionSystem.impl.ActionUpdater - 372 ms to grab EDT for TabListAction#update (com.intellij.ide.actions.TabListAction) 
2021-11-24 11:01:42,073 [   8733]   WARN - ctionSystem.impl.ActionUpdater - 375 ms to grab EDT for RunConfigurationsComboBoxAction#update (com.intellij.execution.actions.RunConfigurationsComboBoxAction) 
2021-11-24 11:01:42,130 [   8790]   INFO - til.indexing.RootChangesLogger - New rootsChanged event for "buildSrc-included-build-dependency" project with full reindex with trace_hash = 562794882:
java.lang.Throwable
	at com.intellij.util.indexing.RootChangesLogger.info(RootChangesLogger.java:27)
	at com.intellij.util.indexing.EntityIndexingServiceImpl.logRootChanges(EntityIndexingServiceImpl.java:99)
	at com.intellij.util.indexing.EntityIndexingServiceImpl.runFullReindex(EntityIndexingServiceImpl.java:84)
	at com.intellij.util.indexing.EntityIndexingServiceImpl.indexChanges(EntityIndexingServiceImpl.java:46)
	at com.intellij.openapi.roots.impl.ProjectRootManagerComponent.synchronizeRoots(ProjectRootManagerComponent.java:298)
	at com.intellij.openapi.roots.impl.ProjectRootManagerComponent.fireRootsChangedEvent(ProjectRootManagerComponent.java:211)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.fireRootsChanged(ProjectRootManagerImpl.java:447)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$1.fireRootsChanged(ProjectRootManagerImpl.java:145)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$1.fireRootsChanged(ProjectRootManagerImpl.java:142)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$BatchSession.rootsChanged(ProjectRootManagerImpl.java:114)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl$BatchSession.rootsChanged(ProjectRootManagerImpl.java:122)
	at com.intellij.openapi.roots.impl.ProjectRootManagerImpl.makeRootsChange(ProjectRootManagerImpl.java:419)
	at org.jetbrains.kotlin.idea.core.script.ucache.ScriptClassRootsUpdater$notifyRootsChanged$$inlined$runInEdt$1$lambda$1.invoke(ScriptClassRootsUpdater.kt:240)
	at org.jetbrains.kotlin.idea.core.script.ucache.ScriptClassRootsUpdater$notifyRootsChanged$$inlined$runInEdt$1$lambda$1.invoke(ScriptClassRootsUpdater.kt:51)
	at org.jetbrains.kotlin.idea.util.application.ApplicationUtilsKt$sam$com_intellij_openapi_util_Computable$0.compute(ApplicationUtils.kt)
	at com.intellij.openapi.application.impl.ApplicationImpl.lambda$runWriteAction$15(ApplicationImpl.java:956)
	at com.intellij.openapi.application.impl.ApplicationImpl.runWriteActionWithClass(ApplicationImpl.java:935)
	at com.intellij.openapi.application.impl.ApplicationImpl.runWriteAction(ApplicationImpl.java:956)
	at org.jetbrains.kotlin.idea.util.application.ApplicationUtilsKt.runWriteAction(ApplicationUtils.kt:24)
	at org.jetbrains.kotlin.idea.core.script.ucache.ScriptClassRootsUpdater$notifyRootsChanged$$inlined$runInEdt$1.run(actions.kt:60)
	at com.intellij.openapi.application.TransactionGuardImpl.runWithWritingAllowed(TransactionGuardImpl.java:214)
	at com.intellij.openapi.application.TransactionGuardImpl.access$200(TransactionGuardImpl.java:21)
	at com.intellij.openapi.application.TransactionGuardImpl$2.run(TransactionGuardImpl.java:196)
	at com.intellij.openapi.application.impl.ApplicationImpl.runIntendedWriteActionOnCurrentThread(ApplicationImpl.java:805)
	at com.intellij.openapi.application.impl.ApplicationImpl.lambda$invokeLater$4(ApplicationImpl.java:348)
	at com.intellij.openapi.application.impl.FlushQueue.doRun(FlushQueue.java:82)
	at com.intellij.openapi.application.impl.FlushQueue.runNextEvent(FlushQueue.java:131)
	at com.intellij.openapi.application.impl.FlushQueue.flushNow(FlushQueue.java:47)
	at com.intellij.openapi.application.impl.FlushQueue$FlushNow.run(FlushQueue.java:187)
	at java.desktop/java.awt.event.InvocationEvent.dispatch(InvocationEvent.java:313)
	at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:776)
	at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:727)
	at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:721)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:85)
	at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:746)
	at com.intellij.ide.IdeEventQueue.defaultDispatchEvent(IdeEventQueue.java:891)
	at com.intellij.ide.IdeEventQueue._dispatchEvent(IdeEventQueue.java:760)
	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$6(IdeEventQueue.java:447)
	at com.intellij.openapi.progress.impl.CoreProgressManager.computePrioritized(CoreProgressManager.java:818)
	at com.intellij.ide.IdeEventQueue.lambda$dispatchEvent$7(IdeEventQueue.java:446)
	at com.intellij.openapi.application.impl.ApplicationImpl.runIntendedWriteActionOnCurrentThread(ApplicationImpl.java:805)
	at com.intellij.ide.IdeEventQueue.dispatchEvent(IdeEventQueue.java:492)
	at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:203)
	at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:124)
	at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:113)
	at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
	at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
	at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
 
2021-11-24 11:01:42,264 [   8924]   INFO - napi.externalSystem.autoimport - [refreshChanges] Settings file status: SettingsFilesStatus(oldCRC={/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build-logic/build-logic-module/build.gradle.kts=882529375, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/settings.gradle.kts=1905800130, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build-logic/settings.gradle.kts=3035320060, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/buildSrc/settings.gradle.kts=2782784733, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/gradle/wrapper/gradle-wrapper.properties=1030805828, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/buildSrc/build.gradle.kts=490474197, /Users/bene/.gradle/gradle.properties=2413959299, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build.gradle.kts=0}, newCRC={/Users/bene/.gradle/gradle.properties=2413959299, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/gradle/wrapper/gradle-wrapper.properties=1030805828, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build-logic/settings.gradle.kts=3035320060, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build-logic/build-logic-module/build.gradle.kts=882529375, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/buildSrc/build.gradle.kts=490474197, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/buildSrc/settings.gradle.kts=2782784733, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build.gradle.kts=0, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/settings.gradle.kts=1905800130}, updated=[], created=[], deleted=[]) 
2021-11-24 11:01:42,401 [   9061]   INFO - System.util.ExternalSystemUtil - External project [/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency] resolution task started 
2021-11-24 11:01:42,462 [   9122]   INFO - CompilerWorkspaceConfiguration - Available processors: 12 
2021-11-24 11:01:42,525 [   9185]   INFO - oad.SharedIndexDownloadService - There is no need to download shared indexes for JdkSharedIndexSuggestion(SharedIndexId(kind=jdk, url=https://index-cdn.jetbrains.com/v2/jdk, indexId=c953d5afd8ac8f025fd907719ba5e02098f0e7b5e3cf135b080bd1ac3cece206#version 11.0.10), lazy). Similar compatible shared indexes are available locally. 
2021-11-24 11:01:42,551 [   9211]   INFO - adleSyncState$SyncStateUpdater - onStart(RESOLVE_PROJECT:0, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency) 
2021-11-24 11:01:42,553 [   9213]   INFO - cState$SyncStateUpdaterService - trackTask(RESOLVE_PROJECT:0, /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency) 
2021-11-24 11:01:42,556 [   9216]   INFO - e.project.sync.GradleSyncState - Started single-variant (TRIGGER_UNKNOWN) sync with Gradle for project 'buildSrc-included-build-dependency'. 
2021-11-24 11:01:42,713 [   9373]   INFO - .imports.GMavenIndexRepository - HTTP not modified since the last request for URL: https://dl.google.com/android/studio/gmaven/index/release/v0.1/classes-v0.1.json.gz (etag: "b0edc8"). 
2021-11-24 11:01:42,713 [   9373]   INFO - .imports.GMavenIndexRepository - Kept the old disk cache with an old ETag header: "b0edc8". 
2021-11-24 11:01:42,905 [   9565]   INFO - s.plugins.gradle.GradleManager - Instructing gradle to use java from /Users/bene/.sdkman/candidates/java/11.0.10.hs-adpt 
2021-11-24 11:01:42,909 [   9569]   INFO - s.plugins.gradle.GradleManager - Instructing gradle to use java from /Users/bene/.sdkman/candidates/java/11.0.10.hs-adpt 
2021-11-24 11:01:43,122 [   9782]   INFO - oad.SharedIndexDownloadService - There is no need to download shared indexes for JdkSharedIndexSuggestion(SharedIndexId(kind=jdk, url=https://index-cdn.jetbrains.com/v2/jdk, indexId=c953d5afd8ac8f025fd907719ba5e02098f0e7b5e3cf135b080bd1ac3cece206#version 11.0.10), lazy). Similar compatible shared indexes are available locally. 
2021-11-24 11:01:43,743 [  10403]   INFO - j.ide.script.IdeStartupScripts - 0 startup script(s) found 
2021-11-24 11:01:43,756 [  10416]   INFO - tor.impl.FileEditorManagerImpl - Project opening took 8140 ms 
2021-11-24 11:01:43,889 [  10549]   INFO - gnostic.PerformanceWatcherImpl - Post-startup activities under progress took 4142ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:44,298 [  10958]   INFO - oject.common.GradleInitScripts - init script file sync.studio.tooling contents "initscript {\n    dependencies {\n        classpath files([mapPath('/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/android/lib/android.jar'), mapPath('/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/android/lib/android.jar'), mapPath('/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/lib/kotlin-stdlib-jdk8.jar')])\n    }\n}\nallprojects {\n    apply plugin: com.android.ide.gradle.model.builder.AndroidStudioToolingPlugin\n}\n" 
2021-11-24 11:01:44,333 [  10993]   INFO - xecution.GradleExecutionHelper - Passing command-line args to Gradle Tooling API: --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/ijmapper.gradle -Didea.sync.active=true -Didea.resolveSourceSetDependencies=true -Porg.gradle.kotlin.dsl.provider.cid=7826549096976 --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/sync.studio.tooling.gradle -Djava.awt.headless=true --stacktrace -Pandroid.injected.build.model.only=true -Pandroid.injected.build.model.only.advanced=true -Pandroid.injected.invoked.from.ide=true -Pandroid.injected.build.model.only.versioned=3 -Pandroid.injected.studio.version=2020.3.1 Final.213.5744.125 -Pandroid.injected.build.model.disable.src.download=true -Pidea.gradle.do.not.build.tasks=false -Pkotlin.mpp.enableIntransitiveMetadataConfiguration=true --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/ijinit.gradle 
2021-11-24 11:01:48,159 [  14819]   INFO - oject.common.GradleInitScripts - init script file sync.studio.tooling contents "initscript {\n    dependencies {\n        classpath files([mapPath('/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/android/lib/android.jar'), mapPath('/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/plugins/android/lib/android.jar'), mapPath('/Users/bene/Library/Application Support/JetBrains/Toolbox/apps/IDEA-C/ch-1/213.5744.125/IntelliJ IDEA CE.app/Contents/lib/kotlin-stdlib-jdk8.jar')])\n    }\n}\nallprojects {\n    apply plugin: com.android.ide.gradle.model.builder.AndroidStudioToolingPlugin\n}\n" 
2021-11-24 11:01:48,174 [  14834]   INFO - xecution.GradleExecutionHelper - Passing command-line args to Gradle Tooling API: --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/ijmapper.gradle --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/ijmapper.gradle -Didea.sync.active=true -Didea.resolveSourceSetDependencies=true -Porg.gradle.kotlin.dsl.provider.cid=7826549096976 --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/sync.studio.tooling.gradle -Djava.awt.headless=true --stacktrace -Pandroid.injected.build.model.only=true -Pandroid.injected.build.model.only.advanced=true -Pandroid.injected.invoked.from.ide=true -Pandroid.injected.build.model.only.versioned=3 -Pandroid.injected.studio.version=2020.3.1 Final.213.5744.125 -Pandroid.injected.build.model.disable.src.download=true -Pidea.gradle.do.not.build.tasks=false -Pkotlin.mpp.enableIntransitiveMetadataConfiguration=true --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/ijinit.gradle --include-build /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency/build-logic -Didea.sync.active=true -Didea.resolveSourceSetDependencies=true -Porg.gradle.kotlin.dsl.provider.cid=7830429764274 --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/sync.studio.tooling1.gradle -Djava.awt.headless=true --stacktrace -Pandroid.injected.build.model.only=true -Pandroid.injected.build.model.only.advanced=true -Pandroid.injected.invoked.from.ide=true -Pandroid.injected.build.model.only.versioned=3 -Pandroid.injected.studio.version=2020.3.1 Final.213.5744.125 -Pandroid.injected.build.model.disable.src.download=true -Pidea.gradle.do.not.build.tasks=false -Pkotlin.mpp.enableIntransitiveMetadataConfiguration=true --init-script /private/var/folders/r3/mg882fyn5553f6wdhfb_6m7c0000gn/T/ijinit1.gradle 
2021-11-24 11:01:48,414 [  15074]   INFO - System.impl.PopupMenuPreloader - Popup menu 'File' preloaded at 'MainMenu' in 100 ms 
2021-11-24 11:01:48,465 [  15125]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Edit' preloaded at 'MainMenu' in 162 ms 
2021-11-24 11:01:48,538 [  15198]   INFO - System.impl.PopupMenuPreloader - Popup menu 'View' preloaded at 'MainMenu' in 235 ms 
2021-11-24 11:01:48,559 [  15219]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Navigate' preloaded at 'MainMenu' in 255 ms 
2021-11-24 11:01:48,808 [  15468]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Code' preloaded at 'MainMenu' in 504 ms 
2021-11-24 11:01:48,846 [  15506]   INFO - ectsystem.ProjectSystemService - GradleProjectSystem project system has been detected 
2021-11-24 11:01:48,852 [  15512]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Refactor' preloaded at 'MainMenu' in 548 ms 
2021-11-24 11:01:48,874 [  15534]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Build' preloaded at 'MainMenu' in 570 ms 
2021-11-24 11:01:49,001 [  15661]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Tools' preloaded at 'MainMenu' in 697 ms 
2021-11-24 11:01:49,008 [  15668]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Run' preloaded at 'MainMenu' in 704 ms 
2021-11-24 11:01:49,031 [  15691]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Git' preloaded at 'MainMenu' in 727 ms 
2021-11-24 11:01:49,039 [  15699]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Window' preloaded at 'MainMenu' in 735 ms 
2021-11-24 11:01:49,057 [  15717]   INFO - .script.IdeScriptEngineManager - javax.script.ScriptEngineManager initialized in 153 ms 
2021-11-24 11:01:49,084 [  15744]   INFO - .project.GradleProjectResolver - Gradle project resolve error 
java.util.NoSuchElementException: Collection contains no element matching the predicate.
	at org.jetbrains.kotlin.idea.gradleJava.configuration.CompilerArgumentsCacheMergeManager.mergeCache(CompilerArgumentsCacheMergeManager.kt:52)
	at org.jetbrains.kotlin.idea.gradleJava.configuration.KotlinCompilerArgumentsCacheResolver.createModule(KotlinCompilerArgumentsCacheResolver.kt:24)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.kotlin.idea.gradleJava.configuration.KotlinMPPGradleProjectResolver.createModule(KotlinMPPGradleProjectResolver.kt:73)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at com.android.tools.idea.gradle.project.sync.idea.AndroidGradleProjectResolver.createModule(AndroidGradleProjectResolver.java:229)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.kotlin.idea.gradleJava.configuration.KotlinGradleProjectResolverExtension.createModule(KotlinGradleProjectResolverExtension.kt:185)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.kotlin.android.configure.KotlinAndroidMPPGradleProjectResolver.createModule(KotlinAndroidMPPGradleProjectResolver.kt:39)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.TracedProjectResolverExtension.createModule(TracedProjectResolverExtension.java:37)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.convertData(GradleProjectResolver.java:404)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.doResolveProjectInfo(GradleProjectResolver.java:322)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:797)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:779)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.lambda$execute$0(GradleExecutionHelper.java:130)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.maybeFixSystemProperties(GradleExecutionHelper.java:157)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.lambda$execute$1(GradleExecutionHelper.java:130)
	at org.jetbrains.plugins.gradle.GradleConnectorService$Companion.withGradleConnection(GradleConnectorService.kt:181)
	at org.jetbrains.plugins.gradle.GradleConnectorService.withGradleConnection(GradleConnectorService.kt)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.execute(GradleExecutionHelper.java:122)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.handleBuildSrcProject(GradleBuildSrcProjectsResolver.java:237)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.lambda$discoverAndAppendTo$0(GradleBuildSrcProjectsResolver.java:164)
	at java.base/java.util.stream.Streams$StreamBuilderImpl.forEachRemaining(Streams.java:411)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:658)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.discoverAndAppendTo(GradleBuildSrcProjectsResolver.java:122)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.resolveProjectInfo(GradleProjectResolver.java:161)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.resolveProjectInfo(GradleProjectResolver.java:74)
	at com.intellij.openapi.externalSystem.service.remote.RemoteExternalSystemProjectResolverImpl.lambda$resolveProjectInfo$0(RemoteExternalSystemProjectResolverImpl.java:37)
	at com.intellij.openapi.externalSystem.service.remote.AbstractRemoteExternalSystemService.execute(AbstractRemoteExternalSystemService.java:43)
	at com.intellij.openapi.externalSystem.service.remote.RemoteExternalSystemProjectResolverImpl.resolveProjectInfo(RemoteExternalSystemProjectResolverImpl.java:36)
	at com.intellij.openapi.externalSystem.service.remote.wrapper.ExternalSystemProjectResolverWrapper.resolveProjectInfo(ExternalSystemProjectResolverWrapper.java:48)
	at com.intellij.openapi.externalSystem.service.internal.ExternalSystemResolveProjectTask.doExecute(ExternalSystemResolveProjectTask.java:115)
	at com.intellij.openapi.externalSystem.service.internal.AbstractExternalSystemTask.execute(AbstractExternalSystemTask.java:151)
	at com.intellij.openapi.externalSystem.service.internal.AbstractExternalSystemTask.execute(AbstractExternalSystemTask.java:135)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$2.executeImpl(ExternalSystemUtil.java:528)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$2.lambda$execute$0(ExternalSystemUtil.java:359)
	at com.intellij.openapi.project.DumbServiceHeavyActivities.suspendIndexingAndRun(DumbServiceHeavyActivities.java:21)
	at com.intellij.openapi.project.DumbServiceImpl.suspendIndexingAndRun(DumbServiceImpl.java:187)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$2.execute(ExternalSystemUtil.java:359)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$4.run(ExternalSystemUtil.java:643)
	at com.intellij.openapi.progress.impl.CoreProgressManager.startTask(CoreProgressManager.java:436)
	at com.intellij.openapi.progress.impl.ProgressManagerImpl.startTask(ProgressManagerImpl.java:120)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcessWithProgressAsync$5(CoreProgressManager.java:496)
	at com.intellij.openapi.progress.impl.ProgressRunner.lambda$submit$3(ProgressRunner.java:244)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcess$2(CoreProgressManager.java:188)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$executeProcessUnderProgress$12(CoreProgressManager.java:624)
	at com.intellij.openapi.progress.impl.CoreProgressManager.registerIndicatorAndRun(CoreProgressManager.java:698)
	at com.intellij.openapi.progress.impl.CoreProgressManager.computeUnderProgress(CoreProgressManager.java:646)
	at com.intellij.openapi.progress.impl.CoreProgressManager.executeProcessUnderProgress(CoreProgressManager.java:623)
	at com.intellij.openapi.progress.impl.ProgressManagerImpl.executeProcessUnderProgress(ProgressManagerImpl.java:66)
	at com.intellij.openapi.progress.impl.CoreProgressManager.runProcess(CoreProgressManager.java:175)
	at com.intellij.openapi.progress.impl.ProgressRunner.lambda$submit$4(ProgressRunner.java:244)
	at java.base/java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1700)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.util.concurrent.Executors$PrivilegedThreadFactory$1$1.run(Executors.java:668)
	at java.base/java.util.concurrent.Executors$PrivilegedThreadFactory$1$1.run(Executors.java:665)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at java.base/java.util.concurrent.Executors$PrivilegedThreadFactory$1.run(Executors.java:665)
	at java.base/java.lang.Thread.run(Thread.java:829)
2021-11-24 11:01:49,164 [  15824]   INFO - adleSyncState$SyncStateUpdater - onFailure(RESOLVE_PROJECT:0, com.intellij.openapi.externalSystem.model.ExternalSystemException: Collection contains no element matching the predicate.) 
2021-11-24 11:01:49,165 [  15825]   INFO - cState$SyncStateUpdaterService - stopTrackingTask(RESOLVE_PROJECT:0) 
2021-11-24 11:01:49,168 [  15828]   WARN - e.project.sync.GradleSyncState - Gradle sync failed: Collection contains no element matching the predicate. (6 s 607 ms) 
2021-11-24 11:01:49,168 [  15828]   WARN - e.project.sync.GradleSyncState - Collection contains no element matching the predicate. 
com.intellij.openapi.externalSystem.model.ExternalSystemException: Collection contains no element matching the predicate.
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionErrorHandler.createUserFriendlyError(GradleExecutionErrorHandler.java:153)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectImportErrorHandler.createUserFriendlyError(AbstractProjectImportErrorHandler.java:46)
	at org.jetbrains.plugins.gradle.service.project.BaseProjectImportErrorHandler.doGetUserFriendlyError(BaseProjectImportErrorHandler.java:167)
	at org.jetbrains.plugins.gradle.service.project.BaseProjectImportErrorHandler.getUserFriendlyError(BaseProjectImportErrorHandler.java:48)
	at org.jetbrains.plugins.gradle.service.project.BaseProjectImportErrorHandler.checkErrorsWithoutQuickFixes(BaseProjectImportErrorHandler.java:62)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$1.getUserFriendlyError(GradleProjectResolver.java:881)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:810)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:779)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.lambda$execute$0(GradleExecutionHelper.java:130)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.maybeFixSystemProperties(GradleExecutionHelper.java:157)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.lambda$execute$1(GradleExecutionHelper.java:130)
	at org.jetbrains.plugins.gradle.GradleConnectorService$Companion.withGradleConnection(GradleConnectorService.kt:181)
	at org.jetbrains.plugins.gradle.GradleConnectorService.withGradleConnection(GradleConnectorService.kt)
	at org.jetbrains.plugins.gradle.service.execution.GradleExecutionHelper.execute(GradleExecutionHelper.java:122)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.handleBuildSrcProject(GradleBuildSrcProjectsResolver.java:237)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.lambda$discoverAndAppendTo$0(GradleBuildSrcProjectsResolver.java:164)
	at java.base/java.util.stream.Streams$StreamBuilderImpl.forEachRemaining(Streams.java:411)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:658)
	at org.jetbrains.plugins.gradle.service.project.GradleBuildSrcProjectsResolver.discoverAndAppendTo(GradleBuildSrcProjectsResolver.java:122)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.resolveProjectInfo(GradleProjectResolver.java:161)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.resolveProjectInfo(GradleProjectResolver.java:74)
	at com.intellij.openapi.externalSystem.service.remote.RemoteExternalSystemProjectResolverImpl.lambda$resolveProjectInfo$0(RemoteExternalSystemProjectResolverImpl.java:37)
	at com.intellij.openapi.externalSystem.service.remote.AbstractRemoteExternalSystemService.execute(AbstractRemoteExternalSystemService.java:43)
	at com.intellij.openapi.externalSystem.service.remote.RemoteExternalSystemProjectResolverImpl.resolveProjectInfo(RemoteExternalSystemProjectResolverImpl.java:36)
	at com.intellij.openapi.externalSystem.service.remote.wrapper.ExternalSystemProjectResolverWrapper.resolveProjectInfo(ExternalSystemProjectResolverWrapper.java:48)
	at com.intellij.openapi.externalSystem.service.internal.ExternalSystemResolveProjectTask.doExecute(ExternalSystemResolveProjectTask.java:115)
	at com.intellij.openapi.externalSystem.service.internal.AbstractExternalSystemTask.execute(AbstractExternalSystemTask.java:151)
	at com.intellij.openapi.externalSystem.service.internal.AbstractExternalSystemTask.execute(AbstractExternalSystemTask.java:135)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$2.executeImpl(ExternalSystemUtil.java:528)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$2.lambda$execute$0(ExternalSystemUtil.java:359)
	at com.intellij.openapi.project.DumbServiceHeavyActivities.suspendIndexingAndRun(DumbServiceHeavyActivities.java:21)
	at com.intellij.openapi.project.DumbServiceImpl.suspendIndexingAndRun(DumbServiceImpl.java:187)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$2.execute(ExternalSystemUtil.java:359)
	at com.intellij.openapi.externalSystem.util.ExternalSystemUtil$4.run(ExternalSystemUtil.java:643)
	at com.intellij.openapi.progress.impl.CoreProgressManager.startTask(CoreProgressManager.java:436)
	at com.intellij.openapi.progress.impl.ProgressManagerImpl.startTask(ProgressManagerImpl.java:120)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcessWithProgressAsync$5(CoreProgressManager.java:496)
	at com.intellij.openapi.progress.impl.ProgressRunner.lambda$submit$3(ProgressRunner.java:244)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcess$2(CoreProgressManager.java:188)
	at com.intellij.openapi.progress.impl.CoreProgressManager.lambda$executeProcessUnderProgress$12(CoreProgressManager.java:624)
	at com.intellij.openapi.progress.impl.CoreProgressManager.registerIndicatorAndRun(CoreProgressManager.java:698)
	at com.intellij.openapi.progress.impl.CoreProgressManager.computeUnderProgress(CoreProgressManager.java:646)
	at com.intellij.openapi.progress.impl.CoreProgressManager.executeProcessUnderProgress(CoreProgressManager.java:623)
	at com.intellij.openapi.progress.impl.ProgressManagerImpl.executeProcessUnderProgress(ProgressManagerImpl.java:66)
	at com.intellij.openapi.progress.impl.CoreProgressManager.runProcess(CoreProgressManager.java:175)
	at com.intellij.openapi.progress.impl.ProgressRunner.lambda$submit$4(ProgressRunner.java:244)
	at java.base/java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1700)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.util.concurrent.Executors$PrivilegedThreadFactory$1$1.run(Executors.java:668)
	at java.base/java.util.concurrent.Executors$PrivilegedThreadFactory$1$1.run(Executors.java:665)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at java.base/java.util.concurrent.Executors$PrivilegedThreadFactory$1.run(Executors.java:665)
	at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: java.util.NoSuchElementException: Collection contains no element matching the predicate.
	at org.jetbrains.kotlin.idea.gradleJava.configuration.CompilerArgumentsCacheMergeManager.mergeCache(CompilerArgumentsCacheMergeManager.kt:52)
	at org.jetbrains.kotlin.idea.gradleJava.configuration.KotlinCompilerArgumentsCacheResolver.createModule(KotlinCompilerArgumentsCacheResolver.kt:24)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.kotlin.idea.gradleJava.configuration.KotlinMPPGradleProjectResolver.createModule(KotlinMPPGradleProjectResolver.kt:73)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at com.android.tools.idea.gradle.project.sync.idea.AndroidGradleProjectResolver.createModule(AndroidGradleProjectResolver.java:229)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.kotlin.idea.gradleJava.configuration.KotlinGradleProjectResolverExtension.createModule(KotlinGradleProjectResolverExtension.kt:185)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.kotlin.android.configure.KotlinAndroidMPPGradleProjectResolver.createModule(KotlinAndroidMPPGradleProjectResolver.kt:39)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension.createModule(AbstractProjectResolverExtension.java:80)
	at org.jetbrains.plugins.gradle.service.project.TracedProjectResolverExtension.createModule(TracedProjectResolverExtension.java:37)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.convertData(GradleProjectResolver.java:404)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver.doResolveProjectInfo(GradleProjectResolver.java:322)
	at org.jetbrains.plugins.gradle.service.project.GradleProjectResolver$ProjectConnectionDataNodeFunction.fun(GradleProjectResolver.java:797)
	... 48 more

2021-11-24 11:01:49,292 [  15952]   INFO - ing.roots.GradleBuildRootIndex - /Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency: org.jetbrains.kotlin.idea.gradleJava.scripting.roots.Imported@794cf3e0 -> org.jetbrains.kotlin.idea.gradleJava.scripting.roots.Imported@5f1ac103 
2021-11-24 11:01:49,299 [  15959]   INFO - System.util.ExternalSystemUtil - External project [/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency] resolution task executed in 6895 ms. 
2021-11-24 11:01:49,875 [  16535]   INFO - ge.ExternalProjectsDataStorage - Save external projects data in 27 ms 
2021-11-24 11:01:49,926 [  16586]   WARN - com.intellij.util.xmlb.Binding - no accessors for org.jetbrains.idea.perforce.perforce.ConnectionId 
2021-11-24 11:01:49,958 [  16618]   INFO - rationStore.ComponentStoreImpl - Saving Project(name=buildSrc-included-build-dependency, containerState=COMPONENT_CREATED, componentStore=/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency)KotlinCommonCompilerArguments took 29 ms 
2021-11-24 11:01:50,322 [  16982]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Help' preloaded at 'MainMenu' in 16 ms 
2021-11-24 11:01:50,324 [  16984]   INFO - oad.SharedIndexDownloadService - There is no need to download shared indexes for JdkSharedIndexSuggestion(SharedIndexId(kind=jdk, url=https://index-cdn.jetbrains.com/v2/jdk, indexId=c953d5afd8ac8f025fd907719ba5e02098f0e7b5e3cf135b080bd1ac3cece206#version 11.0.10), lazy). Similar compatible shared indexes are available locally. 
2021-11-24 11:01:50,404 [  17064]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Project View Popup Menu' preloaded at 'ProjectViewPopup' in 92 ms 
2021-11-24 11:01:50,421 [  17081]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Project View Popup Menu' preloaded at 'ProjectViewPopup' in 108 ms 
2021-11-24 11:01:50,928 [  17588]   INFO - System.impl.PopupMenuPreloader - Popup menu 'Editor Popup Menu' preloaded at 'EditorPopup' in 621 ms 
2021-11-24 11:01:53,526 [  20186]   INFO - indexing.UnindexedFilesUpdater - Scanning completed for buildSrc-included-build-dependency. Number of scanned files: 50529; Number of files for indexing: 4 took 12548ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:53,535 [  20195]   INFO - indexing.UnindexedFilesUpdater - Use 4 indexing threads for indexing of buildSrc-included-build-dependency 
2021-11-24 11:01:53,611 [  20271]   INFO - indexing.UnindexedFilesUpdater - Finished for buildSrc-included-build-dependency. Unindexed files update took 81ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:53,612 [  20272]   INFO - ij.psi.search.LogFileTypeIndex - File type index snapshot dropped 
2021-11-24 11:01:53,617 [  20277]   INFO - ij.psi.search.LogFileTypeIndex - Loading file type index snapshot 
2021-11-24 11:01:53,656 [  20316]   INFO - indexing.UnindexedFilesUpdater - Started indexing of buildSrc-included-build-dependency. Reason: Reindex requested by project root model changes 
2021-11-24 11:01:53,656 [  20316]   INFO - indexing.UnindexedFilesUpdater - Performing delayed pushing properties tasks for buildSrc-included-build-dependency took 0ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:53,657 [  20317]   INFO - indexing.UnindexedFilesUpdater - Scanning of buildSrc-included-build-dependency uses 11 scanning threads 
2021-11-24 11:01:55,644 [  22304]   INFO - rationStore.ComponentStoreImpl - Saving Project(name=buildSrc-included-build-dependency, containerState=COMPONENT_CREATED, componentStore=/Users/bene/workspace/gradle/sample-projects/gradle-sandbox/buildSrc-included-build-dependency)RunManager took 15 ms 
2021-11-24 11:01:58,805 [  25465]   INFO - indexing.UnindexedFilesUpdater - Scanning completed for buildSrc-included-build-dependency. Number of scanned files: 157733; Number of files for indexing: 4 took 5149ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:58,807 [  25467]   INFO - indexing.UnindexedFilesUpdater - Use 4 indexing threads for indexing of buildSrc-included-build-dependency 
2021-11-24 11:01:58,825 [  25485]   INFO - indexing.UnindexedFilesUpdater - Finished for buildSrc-included-build-dependency. Unindexed files update took 19ms; general responsiveness: ok; EDT responsiveness: ok 
2021-11-24 11:01:58,825 [  25485]   INFO - ij.psi.search.LogFileTypeIndex - File type index snapshot dropped 
2021-11-24 11:01:58,880 [  25540]   INFO - .services.SpaceKtsFileDetector - SpaceKtsFileDetector 
```

## Gradle 7.3

When using Gradle 7.3 a bug in Gradle prevents this project from being synced in Intellij, see also https://github.com/gradle/gradle/issues/18984.
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
