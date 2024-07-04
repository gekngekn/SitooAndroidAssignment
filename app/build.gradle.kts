import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.gekn.sitooandroidassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gekn.sitooandroidassignment"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.gekn.sitooandroidassignment.SitooAndroidTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        // Get API credentials from local.properties and store them as BuildConfig fields
        val localProperties = Properties()
        localProperties.load(FileInputStream(rootProject.file("local.properties")))

        debug {
            // Sandbox API credentials
            buildConfigField("String", "API_USER", "\"" + localProperties["api.user"] + "\"")
            buildConfigField("String", "API_PASS", "\"" + localProperties["api.pass"] + "\"")
            isMinifyEnabled = false

        }
        release {
            // Sandbox API credentials
            buildConfigField("String", "API_USER", "\"" + localProperties["api.user"] + "\"")
            buildConfigField("String", "API_PASS", "\"" + localProperties["api.pass"] + "\"")

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        // Enable BuildConfig fields
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    // Def
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation)

    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)

    // OkHttp/Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.okhttp3)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.svg)
    implementation(libs.coil.compose)

    // DI
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt)
    implementation(libs.hilt.testing)
    ksp(libs.hilt.compiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)

    // Kotlinx Test
    testImplementation(libs.kotlinx.coroutines.test)

    // io.mockk
    testImplementation(libs.io.mockk)

    // Hilt
    androidTestImplementation(libs.hilt.testing){
        exclude(group = "androidx.test", module = "core")
    }
    kspAndroidTest(libs.hilt.compiler)

    debugImplementation(libs.androidx.test.core)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.test.monitor)

}