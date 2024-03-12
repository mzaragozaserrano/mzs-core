plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {

    namespace = "com.thecocktailapp.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    flavorDimensions += "architecture"

    productFlavors {
        create("mvi") {
            dimension = "architecture"
        }

        create("mvvm") {
            dimension = "architecture"
        }
    }

}

dependencies {

    //----- ANDROIDX ----------/
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")

    //----- COIL ----------/
    implementation("io.coil-kt:coil-compose:2.5.0")

    //----- COMPOSE ----------/
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-util")

    //----- COROUTINES ----------/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //----- DAGGER ----------/
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    //----- GLIDE ----------/
    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    //-----  LOTTIE ----------/
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.airbnb.android:lottie-compose:6.1.0")

    //----- MATERIAL ----------/
    implementation("com.google.android.material:material:1.10.0")

}