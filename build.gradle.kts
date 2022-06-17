// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunNexusPublic) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunNexusRelease) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunPublic) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunGoogle) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunJcenter) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunCentral) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.jitpackIo) }
        mavenCentral()
        maven {
            setUrl(com.foundation.widget.buildsrc.Repositories.codingMjMaven)
            credentials {
                username = com.foundation.widget.buildsrc.Repositories.codingMjDefName
                password = com.foundation.widget.buildsrc.Repositories.codingMjDefPassword
            }
        }
        mavenLocal()
        google()
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
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunNexusPublic) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunNexusRelease) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunPublic) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunGoogle) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunJcenter) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.aliyunCentral) }
        maven { setUrl(com.foundation.widget.buildsrc.Repositories.jitpackIo) }
        mavenCentral()
        maven {
            setUrl(com.foundation.widget.buildsrc.Repositories.codingMjMaven)
            credentials {
                username = com.foundation.widget.buildsrc.Repositories.codingMjDefName
                password = com.foundation.widget.buildsrc.Repositories.codingMjDefPassword
            }
        }
        mavenLocal()
        google()
    }

}


tasks.register("clean2", Delete::class.java) {
    delete(rootProject.buildDir)

}
