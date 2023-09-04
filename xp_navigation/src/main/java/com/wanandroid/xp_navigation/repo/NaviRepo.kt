package com.wanandroid.xp_navigation.repo

import com.wanandroid.xp_commom.base.BaseRepository
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_commom.network.RetrofitManager
import com.wanandroid.xp_navigation.api.NavigationApi
import com.wanandroid.xp_navigation.bean.NavigationBean
import com.wanandroid.xp_navigation.bean.SystemBean

/**
 * create time : 2023/9/1 16:13
 * create by : xupengpeng
 */
class NaviRepo : BaseRepository() {

    val api = RetrofitManager.instance.getService(NavigationApi::class.java)

    suspend fun getSystem(data: ResLiveData<List<SystemBean>>) = dealResponse({ api.getSystem() }, data)
    suspend fun getNavigation(data: ResLiveData<List<NavigationBean>>) = dealResponse({ api.getNavigation() }, data)
}