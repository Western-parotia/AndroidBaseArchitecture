package com.foundation.app.simple.demo.home.data

data class BannerEntity(
    val desc: String = "",
    val id: Int = 0,
    val imagePath: String? = null,
    val isVisible: Int = 0,
    val order: Int = 0,
    val title: String = "",
    val type: Int = 0,
    val url: String? = null
)