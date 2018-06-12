import sun.misc.Version

object Versions {
//    val compileSdk = 27
//    val minSdk = 21
//    val targetSdk = 27
//
//    val versionCode = 1
//    val versionName = "0.1"

    const val kotlin = "1.2.41"

    const val supportLib = "1.0.0-alpha1"
//  const   val supportLib = "+"
    const val constraintLib = "1.1.0"

    const val archLifecycleExtensions = "1.1.1"
    const val archNavigation = "1.0.0-alpha01"
//    const val archRoom = "2.0.0-alpha1" // Uncomment when Navigation is fixed with AndroidX
    const val archRoom = "2.0.0-alpha1"

    const val timber = "4.7.0"

    const val googlePlayServices = "15.0.1"

    // JSON
    const val moshi = "1.6.0"

    // Testing
    const val espressoCore = "3.1.0-alpha1"
    const val rulesRunner = "1.0.2"
}

object Dependencies {
    // Kotlin
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // Support Libs
    val appCompat = "androidx.appcompat:appcompat:${Versions.supportLib}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLib}"

    // Architecture Components
    val lifecycleExtensions = "android.arch.lifecycle:extensions:${Versions.archLifecycleExtensions}"
    val navigationFragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.archNavigation}"
    val navigationUi = "android.arch.navigation:navigation-ui-ktx:${Versions.archNavigation}"
//    val roomRuntime = "android.arch.persistence.room:runtime:${Versions.archRoom}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.archRoom}"
//    val roomCompiler = "android.arch.persistence.room:compiler:${Versions.archRoom}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.archRoom}"

    // Logging/Crashlytics
    val timberLogging = "com.jakewharton.timber:timber:${Versions.timber}"

    // Google Services
    val googleDrive = "com.google.android.gms:play-services-drive:${Versions.googlePlayServices}"

    // JSON
    val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    // Testing
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val runner = "com.android.support.test:runner:${Versions.rulesRunner}"
    val rules = "com.android.support.test:rules:${Versions.rulesRunner}"

}