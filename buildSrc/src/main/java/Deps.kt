object DepProjectRoot {
    const val NAV_SAFE_ARG =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${DepVersion.DEP_NAV}"
}

object DepJetBrainKotlin {
    const val KOTLIN_STD_LID = "org.jetbrains.kotlin:kotlin-stdlib:${DepVersion.DEP_KOTLIN_VERSION}"
    const val KOTLIN_COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DepVersion.DEP_COROUTINES_ANDROID}"
    const val KOTLIN_COROUTINE_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DepVersion.DEP_COROUTINES_ANDROID_CORE}"
}

object DepGoogleAndroid {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${DepVersion.DEP_GOOGLE_HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${DepVersion.DEP_GOOGLE_HILT}"
    const val ANDROID_MATERIAL =
        "com.google.android.material:material:${DepVersion.DEP_GOOGLE_ANDROID_MATERIAL}"
    const val GSON = "com.google.code.gson:gson:${DepVersion.DEP_GOOGLE_GSON}"
}

object DepAndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${DepVersion.DEP_ANDROID_COREX_KTX}"
    const val LEGACY_SUPPORT =
        "androidx.legacy:legacy-support-v4:${DepVersion.DEP_ANDROIDX_LEGACY_STORE}"
    const val COMPACT = "androidx.appcompat:appcompat:${DepVersion.DEP_ANDROIDX_COMPACT}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${DepVersion.DEP_ANDROIDX_CONSTRAINT_LAYOUT}"
    const val DATA_STORE = "androidx.datastore:datastore-preferences:${DepVersion.DEP_ANDROIDX_DATA_STORE}"
    const val LIFECYCLE_VIEW_MODEL =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${DepVersion.DEP_ANDROIDX_LIFE_CYLE}"
    const val LIFECYCLE_LIVEDATA =
        "androidx.lifecycle:lifecycle-livedata-ktx:${DepVersion.DEP_ANDROIDX_LIFE_CYLE}"
    const val lifecyleCommionJava =
        "androidx.lifecycle:lifecycle-common-java8:${DepVersion.DEP_ANDROIDX_LIFE_CYLE}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${DepVersion.DEP_NAV}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${DepVersion.DEP_NAV}"
    const val roomRuntime = "androidx.room:room-runtime:${DepVersion.DEP_ANDROIDX_ROOM_VERSION}"
    const val roomKtx = "androidx.room:room-ktx:${DepVersion.DEP_ANDROIDX_ROOM_VERSION}"
    const val roomCompiler = "androidx.room:room-compiler:${DepVersion.DEP_ANDROIDX_ROOM_VERSION}"
}

object DepSquare {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DepVersion.DEP_SQAURE_RETROFIT}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${DepVersion.DEP_SQAURE_RETROFIT}"
    const val OK_HTTP = "com.squareup.okhttp3:okhttp:${DepVersion.DEP_SQAURE_HTTP_3}"
    const val OK_HTTP_URL_CONNECTION = "com.squareup.okhttp3:okhttp-urlconnection:${DepVersion.DEP_SQAURE_HTTP_3}"
}

object DepGlide {
    const val core = "com.github.bumptech.glide:glide:${DepVersion.DEP_GLIDE}"
    const val compiler = "com.github.bumptech.glide:compiler:${DepVersion.DEP_GLIDE}"
}

object DepTest {
    const val room = "androidx.room:room-testing:${DepVersion.DEP_ANDROIDX_ROOM_VERSION}"
    const val JUNIT = "junit:junit:${DepVersion.DEP_TEST_JUNIT}"
    const val JUNIT_ANDROID = "androidx.test.ext:junit:${DepVersion.DEP_TEST_JUNIT_ANDROID}"
    const val ioMockk = "io.mockk:mockk:${DepVersion.IO_MOCKK}"
    const val OKHTTP_MOCK_SERVER = "com.squareup.okhttp3:mockwebserver:${DepVersion.DEP_SQAURE_HTTP_3}"
    const val GOOGLE_TRUTH = "com.google.truth:truth:${DepVersion.DEP_TEST_GOOGLE_TRUTH}"
    const val ROBOELECTRIC = "org.robolectric:robolectric:${DepVersion.DEP_TEST_ROBOELECTRIC}"
}

object DepTestAndroidX {
    const val ANDROIDX_CORE_KTX = "androidx.test:core-ktx:${DepVersion.DEP_TEST_ANDROIDX_CORE_KTX}"
    const val ANDROIDX_CORE = "androidx.test:core:${DepVersion.DEP_TEST_ANDROIDX_CORE_KTX}"
    const val ANDROIDX_JUNIT_EXT = "androidx.test.ext:junit:${DepVersion.DEP_TEST_ANDROIDX_JUNIT_EXT}"
    const val ANDROIDX_JUNIT_EXT_KTX = "androidx.test.ext:junit:${DepVersion.DEP_TEST_ANDROIDX_JUNIT_EXT}"

    const val ANDROID_ESPRESSO = "androidx.test.espresso:espresso-core:${DepVersion.depTestAndroidEspresso}"

    const val HILT_TEST = "com.google.dagger:hilt-android-testing:${DepVersion.DEP_GOOGLE_HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${DepVersion.DEP_GOOGLE_HILT}"
}

object DepTestKotlin {
    const val ANDROIDX_TEST_KOTLIN_COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${DepVersion.DEP_COROUTINES_ANDROID_CORE}"
}

object Core {
    const val COMMON = ":core:common"
    const val COMMON_TEST = ":core:common-test"
    const val ANALYTICS = ":core:analytics"
    const val NAVIGATION = ":core:navigation"
    const val PRINCIPLE = ":core:principle"
    const val NETWORK = ":core:network"
    const val STORAGE = ":core:storage"
}
