package com.foundation.app.simple.demo.net.api

import com.foundation.app.simple.demo.entity.BaseApiResponse
import com.foundation.app.simple.demo.home.data.BannerEntity
import retrofit2.Response
import retrofit2.http.GET

/**
 * create by zhusw on 5/20/21 14:34
 */
interface WanAndroidService {

    @GET("banner/json")
    suspend fun getBanner(): Response<BaseApiResponse<List<BannerEntity>>>
}