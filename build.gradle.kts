buildscript {
    val kotlin_version by extra("1.5.0")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.3")
        classpath("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
        classpath("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
    }
}

group = "me.eduardo"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
