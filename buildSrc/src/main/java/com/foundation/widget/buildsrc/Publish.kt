package com.foundation.widget.buildsrc

import java.text.SimpleDateFormat
import java.util.*

/**
 *@Desc:
 *-
 *-
 *create by zhusw on 5/6/21 16:43
 */
object Publish {
    object Version {
        private val timeStamp = getTimeStamp()
            get() {
                "timeStamp: $field".log("Publish===")
                return field
            }
        const val versionCode = 1
        const val versionName = "1.0"
        var versionTimeStamp = "$versionName-$timeStamp"
            private set
    }

}

private fun getTimeStamp(): String {
    return SimpleDateFormat(
        "yyyy-mm-HH-mm-ss",
        Locale.CHINA
    ).format(Date(System.currentTimeMillis()))
}