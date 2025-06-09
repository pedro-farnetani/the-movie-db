plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.tmdb.features.movie.catalog"
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

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core dependencies
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))

    // Compose dependencies
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    // Material Design
    implementation(libs.androidx.material3)

    // Coil for image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    // Test dependencies
    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines)
    testImplementation(libs.test.mockk)
}
