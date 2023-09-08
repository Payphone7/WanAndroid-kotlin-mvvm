package com.wanandroid.xp_commom.network

import android.util.Log
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * create time : 2023/8/29 11:48
 * create by : xupengpeng
 */
class RetrofitManager private constructor(){

    companion object {
        val instance: RetrofitManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitManager()
        }
        private const val TAG = "RetrofitManager"

        private const val BASE_URL = "https://www.wanandroid.com/"

    }

    private var retrofit: Retrofit


    init {
        val logInterceptor = HttpLoggingInterceptor {
            Log.d(TAG, ": Okhttp $it")
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient().newBuilder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .cookieJar(MyCookieJar())
            .addInterceptor(logInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }
}