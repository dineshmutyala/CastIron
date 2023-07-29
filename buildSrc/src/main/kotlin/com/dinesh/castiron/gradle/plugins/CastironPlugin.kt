package com.dinesh.castiron.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

class CastironPlugin: Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            val libs = the<LibrariesForLibs>()
            configureAndroid(libs)
            configureKotlin(libs)
            configureKapt(libs)
        }
    }
}