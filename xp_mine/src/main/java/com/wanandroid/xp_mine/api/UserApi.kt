package com.wanandroid.xp_mine.api

import com.wanandroid.xp_commom.network.BaseRes
import com.wanandroid.xp_mine.bean.LoginBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * create time : 2023/8/31 13:58
 * create by : xupengpeng
 */
interface UserApi {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): BaseRes<LoginBean>
}