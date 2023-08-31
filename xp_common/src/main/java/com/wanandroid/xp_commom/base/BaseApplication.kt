package com.wanandroid.xp_commom.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import com.wanandroid.xp_commom.utils.ToastUtil

/**
 * Author by xupengpeng
 * Date : 2023/8/29 10:59
 */

open class BaseApplication : Application() {
    companion object {
        private const val TAG = "BaseApplication"
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks()
        initToast()
        initMMKV()
    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }

    private fun initToast() {
        ToastUtil.instance.init(this)
    }

    private fun registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {
//                ARouter.getInstance().inject(activity)
                Log.d(TAG, "onActivityCreated: " + activity.javaClass.simpleName)
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG, "onActivityStarted: " + activity.javaClass.simpleName)
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG, "onActivityResumed: " + activity.javaClass.simpleName)
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG, "onActivityPaused: " + activity.javaClass.simpleName)
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG, "onActivityStopped: " + activity.javaClass.simpleName)
            }

            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
    }
}