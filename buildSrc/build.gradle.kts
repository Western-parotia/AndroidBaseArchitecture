plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
}

object Repositories {
    private const val aliyunNexusPublic = "https://maven.aliyun.com/nexus/content/groups/public/"
    private const val aliyunNexusRelease =
        "https://maven.aliyun.com/nexus/content/repositories/releases/"
    private const val aliyunPublic = "https://maven.aliyun.com/repository/public/"
    private const val aliyunGoogle = "https://maven.aliyun.com/repository/google/"
    private const val aliyunJcenter = "https://maven.aliyun.com/repository/jcenter/"
    private const val aliyunCentral = "https://maven.aliyun.com/repository/central/"
    private const val jitpackIo = "https://jitpack.io"

    /**
     * 默认的几个，不包含需要密码
     */
    @kotlin.jvm.JvmStatic
    fun defRepositories(resp: RepositoryHandler) {
        resp.apply {
            maven(aliyunNexusPublic)
            maven(aliyunNexusRelease)
            maven(aliyunPublic)
            maven(aliyunGoogle)
            maven(aliyunJcenter)
            maven(aliyunCentral)
            maven(jitpackIo)

//            可能会影响下载速度，如果需要可以单独放开
//            mavenCentral()
//            mavenLocal()
//            google()
//            过时的jcenter
//            jcenter()
        }
    }
}

repositories {
    Repositories.defRepositories(this)
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
