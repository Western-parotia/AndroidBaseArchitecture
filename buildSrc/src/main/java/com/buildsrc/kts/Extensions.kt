package com.buildsrc.kts

import java.io.File
import java.util.*

/**

 *-
 *-
 *create by zhusw on 5/7/21 14:10
 */
private const val TAG = "buildSrc"
internal fun String.log(secondTag: String) {
    println("$TAG $secondTag $this")
}


fun getProperties(file: File, key: String): String {
    val properties = Properties()
    properties.load(file.inputStream())
    return properties.getProperty(key)
}