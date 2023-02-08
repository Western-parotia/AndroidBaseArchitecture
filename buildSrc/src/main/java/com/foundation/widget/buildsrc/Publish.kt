package com.foundation.widget.buildsrc


/**

 *-
 *-
 *create by zhusw on 5/6/21 16:43
 */

private const val VERSION = "1.1.0"
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
        private const val fileName = "local.properties"
        const val groupId = "com.foundation.app"
        const val artifactId = "activity-fragment"
        const val codingArtifactsRepoUrl = Repositories.codingMjMaven
        val repositoryUserName: String
        val repositoryPassword: String

        init {
            val lp = PropertiesUtils.localProperties
            val name = lp.getProperty("codingArtifactsGradleUsername")
            val password = lp.getProperty("codingArtifactsGradlePassword")
            if (name == null || password == null) {
                throw RuntimeException("请在local.properties添加私有仓库的用户名（codingArtifactsGradleUsername）和密码（codingArtifactsGradlePassword）")
            }
            repositoryUserName = name
            repositoryPassword = password
        }
    }

}
