package com.foundation.service.net.utils

import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient

/**
 * create by zhusw on 5/26/21 11:56
 */

fun OkHttpClient.Builder.addUrlSkill(): OkHttpClient.Builder {
    return RetrofitUrlManager.getInstance().with(this)
}
