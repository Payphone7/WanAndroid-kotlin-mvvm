package com.wanandroid.xp_commom.network

import android.util.Log
import androidx.lifecycle.Observer

/**
 * create time : 2023/8/31 10:14
 * create by : xupengpeng
 */
open class BaseStateObserver<T>() : Observer<BaseRes<T>> {

    override fun onChanged(value: BaseRes<T>) {

        when (value.resState) {
            ResponseState.REQUEST_START -> {
                Log.d("BaseStateObserver", "Observer: start")
                getRespDataStart()
            }
            ResponseState.REQUEST_SUCCESS -> {
                Log.d("BaseStateObserver", "Observer: success")
                if(value.data==null){
                    getRespSuccess()
                }else{
                    getRespDataSuccess(value.data!!)
                }

            }
            ResponseState.REQUEST_FAILED_LOGIN -> {
                Log.d("BaseStateObserver", "Observer: failed")
                getRespDataEnd()
            }
            ResponseState.REQUEST_ERROR -> {
                Log.d("BaseStateObserver", "Observer: error")
                getRespDataEnd()
            }

            else -> {}
        }
    }

    open fun getRespDataStart() {}
    open fun getRespDataSuccess(it: T) {}
    open fun getRespSuccess() {}
    open fun getRespDataEnd() {}
}