// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(libs.plugins.gms.googleServices.get().pluginId) version libs.plugins.gms.googleServices.get().version.requiredVersion apply false
}