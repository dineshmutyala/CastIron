plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.gradlePlugin.android)
    implementation(libs.gradlePlugin.kotlin)
}

gradlePlugin {
    plugins {
        create("CastIronPlugin") {
            id = "cast-iron-plugin"
            implementationClass = "com.dinesh.castiron.gradle.plugins.CastironPlugin"
        }
    }
}