plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.a21l_5812_smd_a2"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.a21l_5812_smd_a2"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")
// RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.0")
// CardView
    implementation("androidx.cardview:cardview:1.0.0")
// Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}