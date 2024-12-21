plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.a19dhjetor2024"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.a19dhjetor2024"
        minSdk = 24
        targetSdk = 34
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
    packaging {
        resources {
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE.txt"
        }
    }
}

dependencies {

    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")
    implementation ("androidx.appcompat:appcompat:1.6.1")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.jbcrypt)

}