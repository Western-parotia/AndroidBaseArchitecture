package com.foundation.app.simple.demo.net.api

import com.foundation.app.simple.demo.base.BaseApiResponse
import com.foundation.app.simple.demo.home.data.BannerEntity
import com.foundation.app.simple.demo.home.data.NewsFeedInfo
import com.foundation.app.simple.demo.home.data.PageInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * create by zhusw on 5/20/21 14:34
 */
interface WanAndroidService {

    @GET("banner/json")
    suspend fun getBanner(): Response<BaseApiResponse<List<BannerEntity>>>

    /**
     * @param pageNo 0 开始
     */
    @GET("article/list/{page}/json")
    suspend fun getNews(@Path("page") pageNo: Int)
            : Response<BaseApiResponse<PageInfo<NewsFeedInfo>>>
}