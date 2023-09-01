package com.wanandroid.xp_project.viewmodel

import com.dycw.xp_project.bean.Project
import com.dycw.xp_project.bean.ProjectType
import com.wanandroid.xp_commom.base.BaseViewModel
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_project.repo.ProjectRepo

/**
 * create time : 2023/9/1 10:35
 * create by : xupengpeng
 */
class ProjectViewModel : BaseViewModel() {

    private val _projectTypeLiveData = ResLiveData<List<ProjectType>>()
    private val _projectListLiveData = ResLiveData<Project>()
    private val _collectLiveData = ResLiveData<String>()

    val projectTypeLiveData = _projectTypeLiveData
    val projectListLiveData = _projectListLiveData
    val collectLiveData = _collectLiveData

    private val repo by lazy {
        ProjectRepo()
    }


    fun getProjectType(){
        launch {
            repo.getProjectTypeList(_projectTypeLiveData)
        }
    }
    fun getProList(currentPage: Int, cid: Int) = launch(
        block = { repo.getProjectList(currentPage, cid, _projectListLiveData) }
    )

    fun collect(id: Int) {
        launch(
            block = { repo.collect(id, _collectLiveData) }
        )
    }

    fun unCollect(id: Int) {
        launch(
            block = { repo.unCollect(id, _collectLiveData) }
        )
    }

}