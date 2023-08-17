plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

hilt {
    enableTransformForLocalTests = true
}

android {
    namespace = AppCoreModuleName.moduleCoreNetwork
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

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(mapOf("path" to Core.STORAGE)))
    implementation(project(mapOf("path" to Core.COMMON)))

    implementation(DepJetBrainKotlin.KOTLIN_COROUTINE_CORE)
    implementation(DepAndroidX.CORE_KTX)
    implementation(DepAndroidX.COMPACT)

    implementation(DepGoogleAndroid.ANDROID_MATERIAL)
    implementation(DepGoogleAndroid.HILT_ANDROID)
    kapt(DepGoogleAndroid.HILT_COMPILER)

    implementation(DepSquare.RETROFIT)
    implementation(DepSquare.RETROFIT_GSON)
    implementation(DepSquare.OK_HTTP)
    implementation(DepSquare.OK_HTTP_URL_CONNECTION)

    // todo move the TestPart to Core.Storage.Test
    testImplementation(project(mapOf("path" to Core.STORAGE)))
    testImplementation(DepTest.JUNIT)
    testImplementation(DepTest.ioMockk)
    testImplementation(DepTest.GOOGLE_TRUTH)
    testImplementation(DepTestAndroidX.HILT_TEST)
    kaptTest(DepTestAndroidX.HILT_COMPILER)
    testImplementation(DepTestKotlin.ANDROIDX_TEST_KOTLIN_COROUTINE)
    testImplementation(DepTest.ROBOELECTRIC)

    androidTestImplementation(DepTest.JUNIT_ANDROID)
    androidTestImplementation(DepTestAndroidX.ANDROID_ESPRESSO)
}
