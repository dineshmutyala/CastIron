import com.dinesh.castiron.gradle.Config.Modules

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.castiron.get().pluginId)
}

dependencies {
    implementation(platform(libs.kotlin.bom))
    implementation(platform(libs.compose.bom))

    implementation(libs.core.ktx)

    implementation(libs.material3)

    implementation(project(Modules.Ui.COMPONENTS))

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.tooling.preview)
}