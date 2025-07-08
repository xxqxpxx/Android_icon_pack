# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in the Android SDK's default proguard configuration.

# Keep icon pack related classes
-keep class com.iconpack.studio.** { *; }

# Keep all drawable resources
-keep class **.R$drawable { *; }

# Keep launcher intent filters
-keep class * {
    @android.annotation.TargetApi *;
}

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Keep XML drawable resources
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Optimize for smaller APK size
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify 