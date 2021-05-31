// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(group = "com.android.tools.build", name = "gradle", version = "4.1.0")
        classpath(
            group = "org.jetbrains.kotlin",
            name = "kotlin-gradle-plugin",
            version = com.foundation.widget.buildsrc.Dependencies.Kotlin.version
        )
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle-backup-backup files
    }
}

allprojects {
    repositories {
        maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }
        maven { setUrl("https://maven.aliyun.com/repository/public/") }
        maven { setUrl("https://maven.aliyun.com/repository/google/") }
        maven { setUrl("https://maven.aliyun.com/repository/jcenter/") }
        maven { setUrl("https://maven.aliyun.com/repository/central/") }
        google()
        jcenter()
        maven { setUrl("https://jitpack.io") }
        maven {
            setUrl(com.foundation.widget.buildsrc.Publish.Maven.getRepoUrl(project))
            credentials {
                username =
                    com.foundation.widget.buildsrc.Publish.Maven.getCodingMavenUsername(project)
                password =
                    com.foundation.widget.buildsrc.Publish.Maven.getCodingMavenPassword(project)
            }
        }
    }

}


tasks.register("clean2", Delete::class.java) {
    delete(rootProject.buildDir)

}
