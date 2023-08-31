package com.xp.wanandroid

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import com.wanandroid.xp_commom.base.BaseApplication

/**
 *
 */
class MyApp : BaseApplication() {


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }


}