package com.dycw.xp_project.api

import com.dycw.xp_project.bean.Project
import com.dycw.xp_project.bean.ProjectType
import com.wanandroid.xp_commom.network.BaseRes
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by stew on 8/5/22.
 * mail: stewforani@gmail.com
 */
interface ProjectApi {

    //获取项目分类
    @GET("project/tree/json")
    suspend fun getProType(): BaseRes<List<ProjectType>>

    //获取项目列表
    @GET("project/list/{page}/json")
    suspend fun getProList(
        @Path("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("cid") cid: Int
    ): BaseRes<Project>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): BaseRes<String>

    //取消收藏（文章列表）
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): BaseRes<String>

}