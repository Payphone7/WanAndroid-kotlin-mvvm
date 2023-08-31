package com.wanandroid.xp_home.api

import com.dycw.xp_home.bean.ArticleBean
import com.dycw.xp_home.bean.BannerBean
import com.wanandroid.xp_commom.network.BaseRes
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * create time : 2023/8/29 18:07
 * create by : xupengpeng
 */
interface HomeApi {

    //首页banner
    @GET("banner/json")
    suspend fun getBanner(): BaseRes<List<BannerBean>>

    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int, @Query("page_size") page_size: Int): BaseRes<ArticleBean>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): BaseRes<String>

    //取消收藏（文章列表）
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): BaseRes<String>
}