# Preserve all classes with the @Composable annotation from obfuscation.
-keep class * {
    @androidx.compose.runtime.Composable *;
}