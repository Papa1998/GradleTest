# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 关闭压缩
#-dontshrink

# 关闭优化
#-dontoptimize

# 关闭混淆
#-dontobfuscate

# 保护MainActivity中的所有方法和成员变量
#-keep class com.papa.gradletest.MainActivity{*;}

# 保护没有被使用的ProGuardTest不被移除
-keep class com.papa.proguard.ProGuardTest
# 保护ProGuardTest中所有被public修饰的内容
#-keepclassmembers class com.papa.proguard.ProGuardTest{
#    public *;
#}

# 保护该包下的类，子包不被保护
#-keep class com.papa.proguard.*

# 保护该包下的所有类，包括子包
#-keep class com.papa.proguard.**

# 保护所有继承ProGuardTest2的子类
#-keep public class * extends com.papa.proguard.subpackage.ProGuardTest2

# 保护所有实现ProGuardTestInterface的子类
#-keep public class * implements com.papa.proguard.ProGuardInterface