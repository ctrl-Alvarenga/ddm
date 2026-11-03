plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.loginfirebase"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.loginfirebase"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    // Mantém só o português: os textos do FirebaseUI ficam em res/values/firebaseui_strings.xml.
    androidResources {
        localeFilters += "pt-rBR"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.firebaseui:firebase-ui-auth:9.1.1")
    implementation("androidx.appcompat:appcompat:1.7.1")
}
