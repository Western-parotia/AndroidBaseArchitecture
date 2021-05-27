package com.foundation.app.simple.demo.net

import com.foundation.app.simple.demo.net.api.ApiUrl
import com.foundation.service.net.utils.addDynamicDomainSkill
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
        }

    fun create(): Retrofit {
        okHttpClientBuilder.addDynamicDomainSkill()
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiUrl.wanandroid_base_url)
            .build()
    }

}