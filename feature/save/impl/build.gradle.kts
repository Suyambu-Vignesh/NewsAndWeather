plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.save.impl"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(mapOf("path" to Core.COMMON)))
    implementation(project(mapOf("path" to Core.ANALYTICS)))
    implementation(project(mapOf("path" to Core.NAVIGATION)))
    implementation(project(mapOf("path" to Core.PRINCIPLE)))
    implementation(project(mapOf("path" to Core.NETWORK)))

    implementation(DepAndroidX.CORE_KTX)
    implementation(DepAndroidX.COMPACT)
    implementation(DepGoogleAndroid.ANDROID_MATERIAL)

    testImplementation(DepTest.GOOGLE_TRUTH)
    testImplementation(DepTest.JUNIT)
    testImplementation(DepTest.ioMockk)

    androidTestImplementation(DepTest.JUNIT_ANDROID)
    androidTestImplementation(DepTestAndroidX.ANDROID_ESPRESSO)
}