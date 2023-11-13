package com.buildsrc.kts


import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

object PropertiesUtils {
    //SdkConstants.FN_GRADLE_PROPERTIES
    val gradleProperties =
        getFileProperties(File(GlobalConfig.rootDirFile, "gradle.properties"))

    /**
     * local可能没有，如：远程构建
     * SdkConstants.FN_LOCAL_PROPERTIES
     */
    val localProperties = getFileProperties(File(GlobalConfig.rootDirFile, "local.properties"))


    /**
     * 读取properties文件
     */
    fun getFileProperties(file: File): Properties {
        val properties = Properties()
        InputStreamReader(FileInputStream(file), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
        return properties
    }
}
