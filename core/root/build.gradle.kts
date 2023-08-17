plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = AppCoreModuleName.moduleCoreRoot
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
    implementation(DepGoogleAndroid.ANDROID_MATERIAL)
    testImplementation(DepTest.JUNIT)
    androidTestImplementation(DepTest.JUNIT_ANDROID)
    androidTestImplementation(DepTestAndroidX.ANDROID_ESPRESSO)
}