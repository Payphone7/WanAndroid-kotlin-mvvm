package com.wanandroid.xp_home.repo

import com.dycw.xp_home.bean.ArticleBean
import com.dycw.xp_home.bean.BannerBean
import com.wanandroid.xp_commom.base.BaseRepository
import com.wanandroid.xp_commom.network.BaseRes
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_commom.network.RetrofitManager
import com.wanandroid.xp_home.api.HomeApi

/**
 * create time : 2023/8/29 18:06
 * create by : xupengpeng
 */
class HomeRepo : BaseRepository() {

    private val api = RetrofitManager.instance.getService(HomeApi::class.java)

    suspend fun getBanner(liveData: ResLiveData<List<BannerBean>>) {
        dealResponse({ api.getBanner() }, liveData)
    }

    suspend fun getArticle(currentPage:Int , data: ResLiveData<ArticleBean>) = dealResponse(
        {api.getArticleList(currentPage,10)},data
    )

    suspend fun unCollect(id: Int, data: ResLiveData<String>) = dealResponse(
        { api.unCollect(id) }, data
    )

    suspend fun collect(id: Int, data: ResLiveData<String>) = dealResponse(
        { api.collect(id) }, data
    )
}