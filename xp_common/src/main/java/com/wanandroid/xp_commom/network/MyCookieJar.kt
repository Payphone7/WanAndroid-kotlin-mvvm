package com.wanandroid.xp_commom.network

import android.util.Log
import com.google.gson.Gson
import com.wanandroid.xp_commom.utils.Constants
import com.wanandroid.xp_commom.utils.MMKVUtil
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class MyCookieJar : CookieJar {

    val gson = Gson()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val s = MMKVUtil.instance.getString(Constants.USER_COOKIE)
        if (s != null) {
            val l = gson.fromJson(s, CookieBean::class.java).list
            Log.d("OkHttp", "loadForRequest: $l")
            return l
        }
        return arrayListOf()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

        if (MMKVUtil.instance.getString(Constants.USER_COOKIE) != null) {
            Log.d("OkHttp", "saveFromResponse:2")
            return
        }

        for (item in cookies) {
            if (item.toString().contains("loginUserName")) {
                Log.d("OkHttp", "saveFromResponse:3")
                MMKVUtil.instance.put(Constants.USER_COOKIE, gson.toJson(CookieBean(list = cookies)))
                break
            }
        }
    }

}
