package com.wanandroid.xp_mine.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.wanandroid.xp_commom.base.BaseViewModel
import com.wanandroid.xp_room.WanAndroidDataBase
import com.wanandroid.xp_room.bean.UserInfoBean

/**
 * create time : 2023/9/5 17:27
 * create by : xupengpeng
 */
class UserInfoViewModel : BaseViewModel() {
    private val _userInfoLiveData = MutableLiveData<List<UserInfoBean>>()

    val userInfoLiveData = _userInfoLiveData


    fun getAllUser(context: Context){
        launch {
        }
    }
}