package com.wanandroid.xp_project.repo

import com.dycw.xp_project.api.ProjectApi
import com.dycw.xp_project.bean.Project
import com.dycw.xp_project.bean.ProjectType
import com.wanandroid.xp_commom.base.BaseRepository
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_commom.network.RetrofitManager

/**
 * create time : 2023/9/1 10:29
 * create by : xupengpeng
 */
class ProjectRepo : BaseRepository() {
    private val api = RetrofitManager.instance.getService(ProjectApi::class.java)
    suspend fun getProjectTypeList(data: ResLiveData<List<ProjectType>>) = dealResponse(
        { api.getProType() }, data
    )

    suspend fun getProjectList(currentPage: Int, cid: Int, data: ResLiveData<Project>) = dealResponse(
        { api.getProList(currentPage, 10, cid) }, data
    )

    suspend fun collect(id: Int, data: ResLiveData<String>) = dealResponse(
        { api.collect(id) }, data
    )

    suspend fun unCollect(id: Int, data: ResLiveData<String>) = dealResponse(
        { api.unCollect(id) }, data
    )
}