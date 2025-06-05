plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.tmdb.core.data.movie"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    // Core
    implementation(project(":core:network"))

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    // Retrofit
    implementation(libs.retrofit.core)

    // Moshi
    implementation(libs.moshi.kotlin)

    // Test dependencies
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    testImplementation(libs.test.mockk)
}