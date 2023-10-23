# Preserve all classes with the @Composable annotation from obfuscation.
-keep class com.mzaragozaserrano.compose.** {
    @androidx.compose.runtime.Composable *;
}