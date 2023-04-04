package com.buildsrc.kts

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

object Repositories {
    private const val aliyunNexusPublic = "https://maven.aliyun.com/nexus/content/groups/public/"
    private const val aliyunPublic = "https://maven.aliyun.com/repository/public/"
    private const val aliyunGoogle = "https://maven.aliyun.com/repository/google/"
    private const val aliyunJcenter = "https://maven.aliyun.com/repository/jcenter/"
    private const val aliyunCentral = "https://maven.aliyun.com/repository/central/"
    private const val jitpackIo = "https://jitpack.io"

    internal const val aliyunReleaseAndArtifacts =
        "https://packages.aliyun.com/maven/repository/2196753-release-jjUEtd/"
    internal const val aliyunSnapshotAndArtifacts =
        "https://packages.aliyun.com/maven/repository/2196753-release-jjUEtd/"

    //公共账号密码，只可用于拉取
    private const val aliyunMjDefName = "642b9f209f62bf75b33fc1ae"
    private const val aliyunMjDefPassword = "EkNR7ao]bCHh"

    internal const val codingMjMaven =
        "https://mijukeji-maven.pkg.coding.net/repository/jileiku/base_maven/"

    //公共账号密码，只可用于拉取
    private const val codingMjDefName = "base_maven-1648105141034"
    private const val codingMjDefPassword = "491ab3340c82a564061c505a8afd99e16d1305b5"

    /**
     * 默认的需要拉的库
     */
    @JvmStatic
    fun defRepositories(resp: RepositoryHandler) {
        resp.apply {
            maven(aliyunNexusPublic)
            maven(aliyunPublic)
            maven(aliyunGoogle)
            maven(aliyunJcenter)
            maven(aliyunCentral)
            maven(jitpackIo)
            mavenPassword(
                aliyunReleaseAndArtifacts,
                aliyunMjDefName,
                aliyunMjDefPassword
            )
            mavenPassword(
                aliyunSnapshotAndArtifacts,
                aliyunMjDefName,
                aliyunMjDefPassword
            )
            mavenPassword(
                codingMjMaven,
                codingMjDefName,
                codingMjDefPassword
            )

//            可能会影响下载速度，如果需要可以单独放开
//            mavenCentral()
//            mavenLocal()
//            google()
//            过时的jcenter
//            jcenter()
        }
    }

    internal fun RepositoryHandler.mavenPassword(url: String, pwdName: String, pwd: String) {
        maven(url) {
            credentials {
                username = pwdName
                password = pwd
            }
        }
    }
}
