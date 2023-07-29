package com.dinesh.castiron.gradle.plugins

import com.dinesh.castiron.gradle.Config
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureKotlin(libs: LibrariesForLibs) {
    tasks.withType(KotlinCompile::class.java) {
        kotlinOptions {
            jvmTarget = libs.versions.kotlin.jvm.get()
        }

        if (findProperty("composeCompilerReports") == "true") {

            println("Configuring module $name to generate Compose compiler reports")

            compilerOptions {
                freeCompilerArgs.addAll(
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${buildDir.absolutePath}/compose_metrics"
                    )
                )
                freeCompilerArgs.addAll(
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${buildDir.absolutePath}/compose_metrics"
                    )
                )
            }
        }
    }
}

fun Project.configureKapt(libs: LibrariesForLibs) {
    if (plugins.hasPlugin(libs.plugins.kotlin.kapt.get().pluginId)) {
        configure<KaptExtension> {
            useBuildCache = true
            correctErrorTypes = true
        }
    }
}