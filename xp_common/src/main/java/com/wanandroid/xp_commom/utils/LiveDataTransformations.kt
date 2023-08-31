package com.wanandroid.xp_commom.utils

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.wanandroid.xp_commom.network.ResLiveData

/**
 * create time : 2023/8/30 17:37
 * create by : xupengpeng
 */

fun <X, Y> map(
    source: LiveData<X>,
    mapFunction: Function<X?, Y>
): LiveData<Y?>? {
    val result = MediatorLiveData<Y>()
    result.addSource(
        source
    ) { x -> result.value = mapFunction.apply(x) }
    return result
}

fun <DATA> ResLiveData<DATA>.map() : LiveData<DATA?> {
    return Transformations.map(this){
        it.data
    }
}