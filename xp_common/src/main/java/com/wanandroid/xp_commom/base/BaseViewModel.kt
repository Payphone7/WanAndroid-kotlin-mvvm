package com.wanandroid.xp_commom.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * create time : 2023/8/29 17:18
 * create by : xupengpeng
 */


open class BaseViewModel : ViewModel() {

    protected fun launch(block: suspend () -> Unit){
        viewModelScope.launch{
            try {
                block.invoke()
            }catch (e:Exception){
                onError(e)
            }
        }
    }

    protected fun onError(e:Exception){

    }
}