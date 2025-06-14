plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    //firebase
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.lecturaparaprimaria"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lecturaparaprimaria"
        minSdk = 23
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))

    //Implementacion Para Datareal

    // Firebase Firestore (Base de datos en tiempo real)
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.0")
    // Firebase Auth (Opcional, si necesitas autenticación)
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    // WorkManager (Para sincronización en segundo plano)
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.7") // Para los iconos
    implementation("androidx.compose.foundation:foundation:1.6.7") // Para LazyVerticalGrid
    implementation("com.google.accompanist:accompanist-pager:0.28.0") // Para el carrusel horizontal
    implementation ("com.google.accompanist:accompanist-pager:0.28.0")
    // Si usas indicadores:
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0")

}