package com.wanandroid.xp_mine

import com.wanandroid.xp_commom.base.BaseViewModel
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_mine.bean.LoginBean
import com.wanandroid.xp_mine.repo.LoginRepo

/**
 * create time : 2023/8/31 15:08
 * create by : xupengpeng
 */
class LoginViewModel : BaseViewModel() {
    private val repo by lazy {
        LoginRepo()
    }

    private val _loginLiveData: ResLiveData<LoginBean> = ResLiveData()

    val loginLiveData = _loginLiveData


    fun login(username: String, password: String) {
        launch(
            block = {
                repo.login(username, password,_loginLiveData)
            }
        )
    }
}