// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false

}
buildscript {
    val composeVersion by extra("1.6.2")
    //val roomVersion by extra("2.3.0")
    val retrofitVersion by extra("2.9.0")
    val okhttpVersion by extra("4.12.0")
    val hiltVersion by extra("2.50")
}
