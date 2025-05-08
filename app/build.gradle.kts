plugins {
    id("com.android.application") version "8.8.0"
}

android {
    namespace = "com.example.apphiep"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apphiep"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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
    implementation("androidx.appcompat:appcompat:1.4.1") // AppCompat for toolbar
    implementation("com.google.android.material:material:1.6.0")  // Material Design for NavigationView
    implementation("androidx.drawerlayout:drawerlayout:1.1.1") // DrawerLayout for the side menu
    implementation("androidx.constraintlayout:constraintlayout:2.1.3") // ConstraintLayout

    // MQTT Libraries
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5") // MQTT Client
    implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1") // MQTT Android Service

    // MPAndroidChart for charts
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Test Libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("net.sourceforge.jtds:jtds:1.3.1")
}
