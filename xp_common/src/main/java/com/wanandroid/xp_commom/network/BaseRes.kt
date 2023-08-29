package com.wanandroid.xp_commom.network

/**
 * create time : 2023/8/29 11:10
 * create by : xupengpeng
 */

/**
 *
{
"data": ...,
"errorCode": 0,
"errorMsg": ""
}
 */
class BaseRes<T> {
    var errorCode: Int = -1
    var errorMsg: String = ""
    var data: T? = null
    var resState : ResponseState? = null


}

enum class ResponseState {
    REQUEST_START,
    REQUEST_SUCCESS,
    REQUEST_FAILED_LOGIN, //登录失效
    REQUEST_ERROR //其他异常

}