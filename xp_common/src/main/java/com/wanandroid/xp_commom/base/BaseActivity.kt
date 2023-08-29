package com.wanandroid.xp_commom.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.ImmersionBar

/**
 * Author by xupengpeng
 * Date : 2023/8/29 10:59
 */

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() ,AbsInterface{

    val TAG = this.javaClass.simpleName

    lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
        mBinding = DataBindingUtil.setContentView(this,getLayoutId())
        init()
        observer()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}