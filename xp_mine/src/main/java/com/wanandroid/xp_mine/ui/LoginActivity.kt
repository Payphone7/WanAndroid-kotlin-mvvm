package com.wanandroid.xp_mine.ui

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.xp_commom.base.BaseActivity
import com.wanandroid.xp_commom.network.BaseStateObserver
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_commom.utils.ToastUtil
import com.wanandroid.xp_mine.LoginViewModel
import com.wanandroid.xp_mine.R
import com.wanandroid.xp_mine.bean.LoginBean
import com.wanandroid.xp_mine.databinding.ActivityLoginBinding

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

        mViewMode.loginLiveData.observe(this, object : BaseStateObserver<LoginBean> (){
            override fun getRespDataSuccess(it: LoginBean) {
                super.getRespDataSuccess(it)
                ToastUtil.instance.showMsg("登录成功")
                finish()
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