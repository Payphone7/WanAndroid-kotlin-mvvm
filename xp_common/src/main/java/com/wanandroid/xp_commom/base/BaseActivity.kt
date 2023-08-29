package com.wanandroid.xp_commom.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Author by xupengpeng
 * Date : 2023/8/29 10:59
 */

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() ,AbsInterface{

    val TAG = this.javaClass.simpleName

    lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,getLayoutId())
        init()
        observer()
    }


}