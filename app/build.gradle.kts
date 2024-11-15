import java.util.Properties

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.bookingapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bookingapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "SUPABASE_ANON_KEY", "${properties["SUPABASE_ANON_KEY"]}")
        buildConfigField("String", "SECRET", "${properties["SECRET"]}")
        buildConfigField("String", "SUPABASE_URL", "${properties["SUPABASE_URL"]}")

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

    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
    kapt {
        correctErrorTypes = true
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Material
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    //Maps
    implementation("com.google.maps.android:android-maps-utils:3.8.2")
    implementation("com.google.maps.android:maps-utils-ktx:5.1.1")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    //Live Data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")

    //View Model


    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation("androidx.fragment:fragment-ktx:1.6.2")

    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    //Supabase
    implementation("io.github.jan-tennert.supabase:supabase-kt:1.2.0")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.2.0")
    implementation("io.ktor:ktor-client-android:2.3.7")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")


    implementation ("com.google.android.libraries.identity.googleid:googleid:1.1.1")

    // optional - needed for credentials support from play services, for devices running
    // Android 13 and below.
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
}