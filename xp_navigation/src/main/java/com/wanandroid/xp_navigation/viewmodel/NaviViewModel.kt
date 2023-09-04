package com.wanandroid.xp_navigation.viewmodel


import com.wanandroid.xp_commom.base.BaseViewModel
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_navigation.bean.NavigationBean
import com.wanandroid.xp_navigation.bean.SystemBean
import com.wanandroid.xp_navigation.repo.NaviRepo

/**
 * Created by stew on 8/7/22.
 * mail: stewforani@gmail.com
 */
class NaviViewModel() : BaseViewModel() {

    private val repo: NaviRepo by lazy {
        NaviRepo()
    }
    private val _navigationLiveData = ResLiveData<List<NavigationBean>>()
    private var _systemLiveData = ResLiveData<List<SystemBean>>()

    val navigationLiveData = _navigationLiveData
    val systemLiveData = _systemLiveData
    fun getSystem() = launch { repo.getSystem(_systemLiveData) }
    fun getNavigation() { launch { repo.getNavigation(_navigationLiveData) }}
}