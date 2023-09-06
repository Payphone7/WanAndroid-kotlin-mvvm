package com.wanandroid.xp_room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wanandroid.xp_room.bean.CoinInfoBean
import com.wanandroid.xp_room.bean.UserInfoBean
import com.wanandroid.xp_room.dao.CoinInfoDao
import com.wanandroid.xp_room.dao.UserInfoDao

/**
 * create time : 2023/9/5 17:01
 * create by : xupengpeng
 */
@Database(entities = [UserInfoBean::class, CoinInfoBean::class], version = 1)
abstract class WanAndroidDataBase : RoomDatabase() {

    companion object {
        private var instance: WanAndroidDataBase? = null
        private const val TAG = "UserDataBase"
        fun get(context:Context):WanAndroidDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(context,WanAndroidDataBase::class.java,"wanAndroid.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(object :Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d(TAG, "onCreate: " + db.path)
                        }
                    }).build()
            }
            return instance!!
        }
    }

    abstract fun userInfoDao(): UserInfoDao
    abstract fun coinInfoDao(): CoinInfoDao
}