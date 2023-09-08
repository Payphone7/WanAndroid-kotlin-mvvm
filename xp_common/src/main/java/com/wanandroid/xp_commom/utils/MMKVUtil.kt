package com.wanandroid.xp_commom.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * create time : 2023/8/29 11:55
 * create by : xupengpeng
 */
class MMKVUtil private constructor(){
    companion object{
        val instance : MMKVUtil by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            MMKVUtil()
        }
    }

    var kv = MMKV.defaultMMKV()

    fun put(key: String,value:Any?){
        when (value) {
            is Boolean -> kv.putBoolean(key, value)
            is ByteArray -> kv.putBytes(key, value)
            is Float -> kv.putFloat(key, value)
            is Int -> kv.putInt(key, value)
            is Long -> kv.putLong(key, value)
            is String -> kv.putString(key, value)
        }
    }

    fun getBoolean(key: String, defValue: Boolean = false) = kv.getBoolean(key, defValue)

    fun getBytes(key: String, defValue: ByteArray? = null) = kv.getBytes(key, defValue)

    fun getFloat(key: String, defValue: Float = 0f) = kv.getFloat(key, defValue)

    fun getInt(key: String, defValue: Int = 0) = kv.getInt(key, defValue)

    fun getLong(key: String, defValue: Long = 0L) = kv.getLong(key, defValue)

    fun getString(key: String, defValue: String? = null) = kv.getString(key, defValue)

    fun remove(key: String) = kv.remove(key)




    fun <T : Parcelable> putParcelable(key: String, t: T): Boolean {
        return kv.encode(key, t)
    }

    inline fun <reified T : Parcelable> getParcelable(key: String): T? {
        return kv.decodeParcelable(key, T::class.java)
    }
}