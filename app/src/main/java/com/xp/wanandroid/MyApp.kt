package com.xp.wanandroid

import android.app.Application
import android.content.Context
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

    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }


}