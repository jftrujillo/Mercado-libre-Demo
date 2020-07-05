object Versions {
    const val kotlin = "1.3.72"
    const val retrofit = "2.9.0"
    const val gson = "2.8.6"
    const val glide = "4.11.0"
    const val coreKtx = "1.3.0"
    const val constraintLayout = "1.1.3"
    const val appCompat = "1.1.0"
    const val recyclerView = "28.0.0"
    const val lifeCycle = "2.2.0"
    const val jUnit = "4.12"
    const val jUnitExtAndroid = "1.1.1"
    const val mockito = "3.3.3"
    const val coroutinesTesting = "1.3.7"
    const val ktLint = "0.33.0"
    const val hilt = "2.28-alpha"
    const val hiltViewModel = "1.0.0-alpha01"
    const val navigationComponent = "2.3.0-beta01"
}

object Dependencies {
    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val retrofitLibrary = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gsonLibrary = "com.google.code.gson:gson:${Versions.gson}"
    const val glideLibrary = "com.github.bumptech.glide:${Versions.glide}"
    const val coreKtxLibrary = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayoutLibrary = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val appCompatLibrary = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val recyclerViewLibrary = "com.android.support:recyclerview-v7:${Versions.recyclerView}"
    const val ktLintLibrary = "com.pinterest:ktlint:${Versions.ktLint}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"


    const val androidViewModelLibrary = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val liveDataLibrary = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"

    const val hiltLibrary = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltViewModelLibrary = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"
}

object AnnotationProcessor {
    const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val hiltAnnotationProcessor = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}

object TestDependencies {
    const val jUnitLibrary = "junit:junit:${Versions.jUnit}"
    const val jUnitExtAndroidLibrary = "androidx.test.ext:junit:${Versions.jUnitExtAndroid}"
    const val mockitoLibrary = "org.mockito:mockito-core:${Versions.mockito}"
    const val coroutinesLibrary = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTesting}"
}