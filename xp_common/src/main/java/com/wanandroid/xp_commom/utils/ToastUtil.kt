package com.wanandroid.xp_commom.utils

import android.app.Application
import android.content.Context
import android.widget.Toast

/**
 * create time : 2023/8/29 11:32
 * create by : xupengpeng
 */

//object ToastUtil
class ToastUtil private constructor(){

    lateinit var mContext: Application

    companion object{
        val instance: ToastUtil by lazy (LazyThreadSafetyMode.SYNCHRONIZED){
            ToastUtil()
        }
    }

    fun init(context: Application){
        mContext = context
    }

    fun showMsg(string: String){
        mContext.apply {
            Toast.makeText(this,string,Toast.LENGTH_SHORT).show()
        }

    }
}