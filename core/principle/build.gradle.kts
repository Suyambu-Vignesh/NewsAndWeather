plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = AppCoreModuleName.moduleCorePrinciple
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
        unitTests.isReturnDefaultValues = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(mapOf("path" to Core.STORAGE)))

    implementation(DepAndroidX.CORE_KTX)
    implementation(DepAndroidX.COMPACT)
    implementation(DepGoogleAndroid.ANDROID_MATERIAL)
    implementation(DepGoogleAndroid.HILT_ANDROID)
    kapt(DepGoogleAndroid.HILT_COMPILER)
    implementation(DepSquare.RETROFIT)
    implementation(DepSquare.RETROFIT_GSON)
    implementation(DepSquare.OK_HTTP)

    testImplementation(DepTest.GOOGLE_TRUTH)
    testImplementation(DepTest.JUNIT)
    testImplementation(DepTest.ioMockk)

    androidTestImplementation(DepTest.JUNIT_ANDROID)
    androidTestImplementation(DepTestAndroidX.ANDROID_ESPRESSO)
}
