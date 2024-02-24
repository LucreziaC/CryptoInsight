plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lucreziacarena.cryptoinsight"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lucreziacarena.cryptoinsight"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://api.coingecko.com/api/v3/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
        buildConfig = true

    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")
    implementation("com.google.accompanist:accompanist-insets:0.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")
    implementation("io.coil-kt:coil-compose:1.4.0")

    // compose
    implementation( "androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.compose.ui:ui-tooling-preview:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.compose.ui:ui-viewbinding:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.compose.material:material-ripple:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.activity:activity-compose:${rootProject.extra["composeVersion"]}")
    implementation( "androidx.compose.runtime:runtime-livedata:${rootProject.extra["composeVersion"]}")

    // network
    implementation( "com.squareup.retrofit2:retrofit:${rootProject.extra["retrofitVersion"]}")
    implementation( "com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofitVersion"]}")
    implementation( "com.squareup.okhttp3:okhttp:${rootProject.extra["okhttpVersion"]}")
    implementation( "com.squareup.okhttp3:okhttp-urlconnection:${rootProject.extra["okhttpVersion"]}")
    implementation( "com.squareup.okhttp3:logging-interceptor:${rootProject.extra["okhttpVersion"]}")
    implementation("org.conscrypt:conscrypt-android:2.2.1")

    // room database
    //implementation "androidx.room:room-runtime:$roomVersion"
    //kapt "androidx.room:room-compiler:$roomVersion"

    // dependency injection
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hiltVersion"]}")
    kapt( "com.google.dagger:hilt-compiler:${rootProject.extra["hiltVersion"]}")
    kapt( "androidx.hilt:hilt-compiler:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
}
kapt {
    correctErrorTypes = true
}
