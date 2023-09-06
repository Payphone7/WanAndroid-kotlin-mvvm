package com.wanandroid.xp_room.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wanandroid.xp_room.util.RoomConstants

/**
 * create time : 2023/9/5 15:42
 * create by : xupengpeng
 */
class UserInfoData(
    var coinInfo: CoinInfoBean,
    var userInfo: UserInfoBean,
)

@Entity(tableName = RoomConstants.coinInfoName)
data class CoinInfoBean(
    @ColumnInfo(name = "coinCount") var coinCount: Int, // 可用
    @ColumnInfo(name = "level") var level: Int, // 可用
    @ColumnInfo(name = "nickname") var nickname: String,
    @ColumnInfo(name = "rank") var rank: String, // 可用
    @PrimaryKey var userId: Int, // 可用
    @ColumnInfo(name = "username") var username: String
)

@Entity(tableName = RoomConstants.userInfoName)
data class UserInfoBean(
    @ColumnInfo(name = "admin") var admin: Boolean,
//    @ColumnInfo(name = "chapterTops") var chapterTops: Array<Any>,
    @ColumnInfo(name = "coinCount") var coinCount: Int,
//    @ColumnInfo(name = "collectIds") var collectIds: Array<String>,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "icon") var icon: String,
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "nickname") var nickname: String,
    @ColumnInfo(name = "publicName") var publicName: String,
    @ColumnInfo(name = "token") var token: String,
    @ColumnInfo(name = "username") var username: String
)