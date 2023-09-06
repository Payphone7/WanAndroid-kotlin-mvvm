package com.wanandroid.xp_mine.ui

import android.util.Log
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.xp_commom.base.BaseActivity
import com.wanandroid.xp_commom.network.BaseStateObserver
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_commom.utils.ToastUtil
import com.wanandroid.xp_mine.viewmodel.LoginViewModel
import com.wanandroid.xp_mine.R
import com.wanandroid.xp_mine.bean.LoginBean
import com.wanandroid.xp_mine.databinding.ActivityLoginBinding
import com.wanandroid.xp_room.bean.UserInfoData
import kotlin.math.log

@Route(path = Constants.PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val mViewMode by viewModels<LoginViewModel>()
    override fun init() {

    }

    override fun observer() {
        mBinding.tvLogin.setOnClickListener {
            if (checkInput()) {
                mViewMode.login(
                    mBinding.etName.text.toString(),
                    mBinding.etPassWord.text.toString()
                )
            }
        }

        mViewMode.loginLiveData.observe(this, object : BaseStateObserver<LoginBean>() {
            override fun getRespDataSuccess(it: LoginBean) {
                super.getRespDataSuccess(it)
                mViewMode.userInfo()
//                finish()
            }
        })

        mViewMode.userInfoLiveData.observe(this, object : BaseStateObserver<UserInfoData>() {
            override fun getRespDataSuccess(it: UserInfoData) {
                super.getRespDataSuccess(it)
                Log.d(TAG, "getRespDataSuccess: " + it.userInfo.toString())
                mViewMode.insertUserInfo(this@LoginActivity, it.userInfo)
                mViewMode.insertCoinInfo(this@LoginActivity, it.coinInfo)
                UserInfoManager.instance.setUserLogin(true)
                mBinding.tvLogin.postDelayed({
                    finish()
                },2000)
            }
        })
    }


    private fun checkInput(): Boolean {
        if (mBinding.etName.text.toString().isBlank()) {
            ToastUtil.instance.showMsg("请输入用户名")
            return false
        }
        if (mBinding.etPassWord.text.toString().isBlank()) {
            ToastUtil.instance.showMsg("请输入密码")
            return false
        }
        return true
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}