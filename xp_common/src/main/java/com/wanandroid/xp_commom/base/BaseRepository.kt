package com.wanandroid.xp_commom.base

import com.wanandroid.xp_commom.network.*
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_commom.utils.ToastUtil
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * create time : 2023/8/29 11:05
 * create by : xupengpeng
 */
open class BaseRepository {

    suspend fun <T> dealResponse(block:suspend () -> BaseRes<T>, liveData: ResLiveData<T>){
        var result =  BaseRes<T>()
        result.resState = ResponseState.REQUEST_START
        liveData .value = result

        try {
            result = block.invoke()
            when(result.errorCode){
                Constants.HTTP_SUCCESS ->{
                    result.resState = ResponseState.REQUEST_SUCCESS
                }
                Constants.HTTP_AUTH_INVALID ->{
                    result.resState = ResponseState.REQUEST_FAILED_LOGIN
                }
                else ->{
                    result.resState = ResponseState.REQUEST_ERROR
                }
            }
        }catch (e:Exception){
            when (e) {
                is UnknownHostException,
                is HttpException,
                is ConnectException,
                -> {
                    //网络error
                    ToastUtil.instance.showMsg("网络错误！")
                }
                else -> {
                    ToastUtil.instance.showMsg("网络错误！")
                }
            }
            result.resState = ResponseState.REQUEST_ERROR
        }finally {
            liveData.value = result
        }
    }
}