package com.wanandroid.xp_mine.viewmodel

import android.content.Context
import com.wanandroid.xp_commom.base.BaseViewModel
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_mine.bean.LoginBean
import com.wanandroid.xp_mine.repo.LoginRepo
import com.wanandroid.xp_room.WanAndroidDataBase
import com.wanandroid.xp_room.bean.CoinInfoBean
import com.wanandroid.xp_room.bean.UserInfoBean
import com.wanandroid.xp_room.bean.UserInfoData

/**
 * create time : 2023/8/31 15:08
 * create by : xupengpeng
 */
class LoginViewModel : BaseViewModel() {
    private val repo by lazy {
        LoginRepo()
    }

    private val _loginLiveData: ResLiveData<LoginBean> = ResLiveData()
    private val _userInfoLiveData: ResLiveData<UserInfoData> = ResLiveData()

    val loginLiveData = _loginLiveData
    val userInfoLiveData = _userInfoLiveData


    fun login(username: String, password: String) {
        launch(
            block = {
                repo.login(username, password, _loginLiveData)
            }
        )
    }

    fun userInfo() {
        launch(
            block = {
                repo.userInfo(_userInfoLiveData)
            }
        )
    }

    fun insertUserInfo(context: Context, userInfoBean: UserInfoBean) {
        launch {
            WanAndroidDataBase.get(context).userInfoDao().insetAll(userInfoBean)
        }
    }

    fun insertCoinInfo(context: Context, coinInfoBean: CoinInfoBean) {
        launch {
            WanAndroidDataBase.get(context).coinInfoDao().insetAll(coinInfoBean)
        }
    }


}