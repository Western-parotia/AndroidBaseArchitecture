package com.foundation.service.net.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.foundation.service.net.NetException
import com.foundation.service.net.NetManager
import com.google.gson.JsonSyntaxException
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

/**
 * create by zhusw on 5/26/21 11:56
 */
private const val TAG = "base-net"
internal fun String.log(secondTag: String = "") {
    if (NetManager.debug) {
        Log.i(TAG, "$secondTag:$this")
    }
}

fun OkHttpClient.Builder.addDynamicDomainSkill(): OkHttpClient.Builder {
    return RetrofitUrlManager.getInstance().with(this)
}

internal fun networkIsAvailable(context: Context): Boolean {
    val cm = ContextCompat.getSystemService(
        context,
        ConnectivityManager::class.java
    )
    cm?.let {
        return it.activeNetworkInfo?.isAvailable ?: false
    }
    return false
}

/**
 * 匹配属于 http 的异常，包装为[NetException]返回
 * 如果没有匹配到则返回原始异常
 */
internal fun transformHttpException(e: Throwable): Throwable {
    return when (e) {
        is JSONException,
        is JsonSyntaxException -> {
            return NetException.createNormalType("数据解析异常", e)
        }
        is HttpException -> {
            return NetException.createConnectType("Http 异常 code:${e.code()} msg:${e.message()}", e)
        }
        is UnknownHostException -> {
            return NetException.createConnectType("访问的目标主机不存在", e)
        }
        is SSLException -> {
            return NetException.createConnectType("无法与目标主机建立链接", e)
        }
        is SocketTimeoutException,
        is ConnectException -> {
            return NetException.createConnectType("网络链接异常", e)
        }
        is TimeoutException -> {
            return NetException.createConnectType("网络链接超时", e)
        }
        else -> {
            e
        }
    }
}
