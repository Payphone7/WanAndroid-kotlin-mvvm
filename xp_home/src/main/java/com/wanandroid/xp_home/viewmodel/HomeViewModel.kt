package com.wanandroid.xp_home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dycw.xp_home.bean.ArticleBean
import com.dycw.xp_home.bean.BannerBean
import com.wanandroid.xp_commom.base.BaseViewModel
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_commom.utils.map
import com.wanandroid.xp_home.repo.HomeRepo

/**
 * create time : 2023/8/29 17:17
 * create by : xupengpeng
 */
class HomeViewModel() : BaseViewModel() {

    private val _bannerLiveData = ResLiveData<List<BannerBean>>()

    private var _articleLiveData = ResLiveData<ArticleBean>()

    private var _collectLiveData = ResLiveData<String>()

    var bannerLiveData = _bannerLiveData
    var articleLiveData = _articleLiveData
    var collectLiveData = _collectLiveData

    private val repo = HomeRepo()

    fun getBanner() = launch {
        repo.getBanner(_bannerLiveData)
    }

    fun getArticle(currentPage: Int) = launch {
        repo.getArticle(currentPage, _articleLiveData)
    }

    fun collect(id: Int) = launch { repo.collect(id, _collectLiveData) }
    fun unCollect(id: Int) = launch { repo.unCollect(id, _collectLiveData) }

}
