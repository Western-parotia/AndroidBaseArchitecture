// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.buildsrc.kts.GlobalConfig

//buildSrc的初始化init
GlobalConfig.init(project)

buildscript {//这里不支持import
    repositories {
        com.buildsrc.kts.Repositories.defRepositories(this)
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${com.buildsrc.kts.Dependencies.kotlinVersion}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        com.buildsrc.kts.Repositories.defRepositories(this)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}