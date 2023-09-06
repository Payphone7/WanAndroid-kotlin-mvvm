package com.wanandroid.xp_mine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * create time : 2023/9/5 16:18
 * create by : xupengpeng
 */
class UserInfoManager {
    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            UserInfoManager()
        }
    }

    val userLoginLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun setUserLogin(login: Boolean) {
        userLoginLiveData.value = login
    }
}