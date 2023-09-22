package com.wanandroid.xp_commom.base

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.elvishew.xlog.XLog
import com.gyf.immersionbar.ImmersionBar
import java.util.Arrays

/**
 * Author by xupengpeng
 * Date : 2023/8/29 10:59
 */

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() ,AbsInterface{

    val TAG = this.javaClass.simpleName

    lateinit var mBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XLog.d(TAG + "onCreate")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 获取系统window支持的模式
            val modes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                display!!.supportedModes
            }else{
                window.windowManager.defaultDisplay.supportedModes
            }
            // 对获取的模式，基于刷新率的大小进行排序，从小到大排序
            modes.sortBy {
                it.refreshRate
            }

            modes.forEach {
                Log.d(TAG, "onCreate: modes refreshRate" + it.refreshRate)
            }
            window.let {
                val lp = it.attributes
                // 取出最大的那一个刷新率，直接设置给window
                lp.preferredDisplayModeId = modes.last().modeId
                it.attributes = lp
            }
        }

        ImmersionBar.with(this).init()
        mBinding = DataBindingUtil.setContentView(this,getLayoutId())
        init()
        observer()
    }

    override fun onResume() {
        super.onResume()
        XLog.d(TAG + "onResume")

    }

    override fun onPause() {
        super.onPause()
        XLog.d(TAG + "onPause")

    }


    override fun onDestroy() {
        super.onDestroy()
        XLog.d(TAG + "onDestroy")
    }


}