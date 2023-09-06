package com.wanandroid.xp_room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wanandroid.xp_room.bean.CoinInfoBean
import com.wanandroid.xp_room.bean.UserInfoBean

/**
 * create time : 2023/9/5 17:00
 * create by : xupengpeng
 */
@Dao
interface UserInfoDao {
    @Query("SELECT * FROM userInfoName")
    fun getAll(): List<UserInfoBean>

    @Query("SELECT *FROM userInfoName WHERE id LIKE:userId ")
     fun findById(userId: Int): UserInfoBean

    @Insert
     fun insetAll(vararg userInfoBean: UserInfoBean)

    @Delete
     fun delete(userInfoBean: UserInfoBean)
}


@Dao
interface CoinInfoDao {
    @Query("SELECT * FROM coinInfoName")
     fun getAll(): List<CoinInfoBean>

    @Query("SELECT *FROM coinInfoName WHERE userId LIKE:userId ")
     fun findById(userId: Int): CoinInfoBean

    @Insert
     fun insetAll(vararg coinInfoBean: CoinInfoBean)
    @Delete
     fun delete(coinInfoBean : CoinInfoBean)
}