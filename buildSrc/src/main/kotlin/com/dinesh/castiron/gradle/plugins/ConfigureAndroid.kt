package com.dinesh.castiron.gradle.plugins

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.dinesh.castiron.gradle.Config
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureAndroid(libs: LibrariesForLibs) {


    if (plugins.hasPlugin(libs.plugins.java.lib.get().pluginId) || plugins.hasPlugin(libs.plugins.kotlin.jvm.get().pluginId)) return

    configureAndroidCommon(libs)
    if (plugins.hasPlugin(libs.plugins.android.application.get().pluginId)) configureAndroidApp(libs)
    else if (plugins.hasPlugin(libs.plugins.android.library.get().pluginId)) configureAndroidLibrary(libs)
}

private fun Project.configureAndroidCommon(libs: LibrariesForLibs) {
    configure<BaseExtension> {
        namespace = Config.Modules.getNamespace(name, libs)

        println("Configured module $name with namespace $namespace")

        defaultConfig {
            minSdk = libs.versions.sdk.min.get().toInt()
            targetSdk = libs.versions.sdk.target.get().toInt()
        }

        buildTypes {
            named("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        if (!plugins.hasPlugin(libs.plugins.skip.compose.get().pluginId)) {
            buildFeatures.apply {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = libs.versions.sdk.compose.compiler.get()
            }
        }
    }
}

private fun Project.configureAndroidApp(libs: LibrariesForLibs) {
    configure<BaseAppModuleExtension> {
        compileSdk = libs.versions.sdk.compile.get().toInt()

        defaultConfig {
            applicationId = libs.versions.application.id.get()
            versionCode = libs.versions.application.version.code.get().toInt()
            versionName = libs.versions.application.version.name.get()
        }
    }
}

private fun Project.configureAndroidLibrary(libs: LibrariesForLibs) {
    configure<LibraryExtension> {

        compileSdk = libs.versions.sdk.compile.get().toInt()

        defaultConfig.apply {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}
