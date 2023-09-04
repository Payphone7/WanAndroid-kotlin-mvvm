package com.wanandroid.xp_navigation.api
import com.wanandroid.xp_navigation.bean.NavigationBean
import com.wanandroid.xp_navigation.bean.SystemBean
import com.wanandroid.xp_commom.network.BaseRes
import retrofit2.http.GET

/**
 * Created by stew on 8/7/22.
 * mail: stewforani@gmail.com
 */
interface NavigationApi {

    //获取导航
    @GET("/navi/json")
    suspend fun getNavigation(): BaseRes<List<NavigationBean>>

    //获取体系
    @GET("/tree/json")
    suspend fun getSystem(): BaseRes<List<SystemBean>>
}