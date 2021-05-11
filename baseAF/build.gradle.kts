import com.foundation.widget.buildsrc.Dependencies
import com.foundation.widget.buildsrc.Publish

plugins {
    id("com.android.library")
    id("kotlin-android")
    `maven-publish`
}

apply("common.gradle")

val versionTimestamp = Publish.Version.getVersionTimestamp()

android {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
//            consumerProguardFiles("consumer-rules.pro") lib单独打包 此属性是无效的
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    afterEvaluate {
        buildTypes.forEach {
            it.buildConfigField("Integer", "versionCode", Publish.Version.versionCode.toString())
            it.buildConfigField("String", "versionName", "\"${Publish.Version.versionName}\"")
            it.buildConfigField("String", "versionTimeStamp", "\"$versionTimestamp\"")
        }
    }
    sourceSets {
        getByName("main") {
            java {
                srcDirs("src/main/java")
            }
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.kotlin_stdlib)
    implementation(Dependencies.AndroidX.core_ktx)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.Glide.glide)
}

val sourceCodeTask: Jar = tasks.register("sourceCode", Jar::class.java) {
    from(android.sourceSets.getByName("main").java.srcDirs)
    classifier = "sources"
}.get()


tasks.register("createGitTagAndPush", Exec::class.java) {
    commandLine("git", "push", "origin", versionTimestamp)
}.get().dependsOn(tasks.register("createGitTag", Exec::class.java) {
    commandLine("git", "tag", versionTimestamp, "-m", "autoCreateWithMavenPublish")
})

publishing {
    val versionName = Publish.Version.versionName
    val groupId = Publish.Maven.groupId
    val artifactId = Publish.Maven.artifactId

    publications {
        create<MavenPublication>("LoadingView") {
            setGroupId(groupId)
            setArtifactId(artifactId)
            version = versionName
            artifact(sourceCodeTask)
            afterEvaluate {//在脚本读取完成后绑定
                val bundleReleaseAarTask: Task = tasks.getByName("bundleReleaseAar")
                bundleReleaseAarTask.finalizedBy("createGitTagAndPush")
                artifact(bundleReleaseAarTask)
            }
//            artifact("$buildDir/outputs/aar/loading-release.aar")//直接制定文件
            " allDependencies=${configurations.implementation.get().allDependencies.size}".log("dep============")
            pom.withXml {
                val dependenciesNode = asNode().appendNode("dependencies")
                configurations.implementation.get().allDependencies.forEach {
                    if (it.version != "unspecified" && it.name != "unspecified") {
                        val depNode = dependenciesNode.appendNode("dependency")
                        depNode.appendNode("groupId", it.group)
                        depNode.appendNode("artifactId", it.name)
                        depNode.appendNode("version", it.version)
                    }
                }
            }

        }
        repositories {
            maven {
                setUrl(Publish.Maven.codingArtifactsRepoUrl)
                credentials {
                    username = Publish.Maven.codingArtifactsGradleUsername
                    password = Publish.Maven.codingArtifactsGradlePassword
                }
            }
        }
    }

}


fun String.log(tag: String) {
    println("$tag:$this")
}