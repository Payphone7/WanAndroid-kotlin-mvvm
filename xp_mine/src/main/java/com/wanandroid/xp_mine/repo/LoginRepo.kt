package com.wanandroid.xp_mine.repo


import com.wanandroid.xp_commom.base.BaseRepository
import com.wanandroid.xp_commom.network.ResLiveData
import com.wanandroid.xp_commom.network.RetrofitManager
import com.wanandroid.xp_mine.api.UserApi
import com.wanandroid.xp_mine.bean.LoginBean

/**
 * Created by stew on 8/21/22.
 * mail: stewforani@gmail.com
 */
class LoginRepo() : BaseRepository() {

   private val api = RetrofitManager.instance.getService(UserApi::class.java)

    suspend fun login(username: String, password: String, data: ResLiveData<LoginBean>) =
        dealResponse(
            block = { api.login(username, password) }, data
        )

}