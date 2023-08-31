package com.wanandroid.xp_mine.ui

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.wanandroid.xp_commom.base.BaseActivity
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_mine.LoginViewModel
import com.wanandroid.xp_mine.R
import com.wanandroid.xp_mine.databinding.ActivityLoginBinding

@Route(path = Constants.PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    val mViewMode by viewModels<LoginViewModel>()
    override fun init() {

    }

    override fun observer() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}