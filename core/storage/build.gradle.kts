plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = AppCoreModuleName.moduleCoreStorage
    compileSdk = AppConfig.minCompileSdk

    defaultConfig {
        minSdk = AppConfig.appBuildMinSdk
        targetSdk = AppConfig.appTargetSdk

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
    implementation(DepAndroidX.CORE_KTX)
    implementation(DepAndroidX.COMPACT)
    implementation(DepAndroidX.DATA_STORE)

    implementation(DepGoogleAndroid.ANDROID_MATERIAL)
    implementation(DepAndroidX.CORE_KTX)
    implementation(DepTest.ioMockk)
    implementation(DepGoogleAndroid.HILT_ANDROID)
    implementation(DepTestAndroidX.HILT_TEST)

    kapt(DepGoogleAndroid.HILT_COMPILER)

    testImplementation(project(mapOf("path" to Core.COMMON_TEST)))
    testImplementation(DepTestAndroidX.ANDROIDX_CORE)
    testImplementation(DepTestKotlin.ANDROIDX_TEST_KOTLIN_COROUTINE)
    testImplementation(DepTest.GOOGLE_TRUTH)
    testImplementation(DepTest.JUNIT)
    testImplementation(DepTest.JUNIT_ANDROID)
    testImplementation(DepTest.ROBOELECTRIC)
    testImplementation(DepTestAndroidX.HILT_TEST)
    kaptTest(DepTestAndroidX.HILT_COMPILER)
    testImplementation(DepTestKotlin.ANDROIDX_TEST_KOTLIN_COROUTINE)

    androidTestImplementation(DepTest.JUNIT_ANDROID)
    androidTestImplementation(DepTestAndroidX.ANDROID_ESPRESSO)
}