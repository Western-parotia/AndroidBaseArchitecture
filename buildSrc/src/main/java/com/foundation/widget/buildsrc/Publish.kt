package com.foundation.widget.buildsrc


/**

*-
 *-
 *create by zhusw on 5/6/21 16:43
 */

private const val VERSION = "1.0"
private const val SNAPSHOT = true

object Publish {
    object Version {
        var versionName = VERSION
            private set
            get() = when (SNAPSHOT) {
                true -> "$field-SNAPSHOT"
                false -> field
            }
        const val versionCode = 1

        private fun getTimestamp(): String {
            return java.text.SimpleDateFormat(
                "yyyy-MM-dd-hh-mm-ss",
                java.util.Locale.CHINA
            ).format(java.util.Date(System.currentTimeMillis()))
        }

        fun getVersionTimestamp(): String {
            return "$versionName-${getTimestamp()}"
        }
    }

    object Maven {
        val codingArtifactsRepoUrl =
            "https://mijukeji-maven.pkg.coding.net/repository/jileiku/base_maven/"
        val codingArtifactsGradleUsername = "base_maven-1620455955399"
        val codingArtifactsGradlePassword = "cf195a4e8b93afea3e5e0f6e538f62687c67cee7"

        val groupId = "com.foundation.app"
        val artifactId = "af"

    }

}
