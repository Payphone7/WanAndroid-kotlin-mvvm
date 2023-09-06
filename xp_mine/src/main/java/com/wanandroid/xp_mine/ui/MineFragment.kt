package com.wanandroid.xp_mine.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.wanandroid.xp_commom.base.BaseFragment
import com.wanandroid.xp_commom.utils.ToastUtil
import com.wanandroid.xp_mine.R
import com.wanandroid.xp_mine.databinding.FragmentMineBinding
import com.wanandroid.xp_mine.viewmodel.UserInfoViewModel
import com.wanandroid.xp_room.WanAndroidDataBase
import com.wanandroid.xp_room.bean.UserInfoBean
import kotlin.math.log

/**
 * create time : 2023/9/5 15:18
 * create by : xupengpeng
 */
class MineFragment : BaseFragment<FragmentMineBinding>() {

    private val mViewModel by viewModels<UserInfoViewModel> ()

    override fun observer() {
        UserInfoManager.instance.userLoginLiveData.observe(this, Observer {
            if (it){
                val list  =  WanAndroidDataBase.get(mContext).userInfoDao().getAll()
                Log.d(TAG, "observer: " + list[0].toString())
            }
        })

        mViewModel.userInfoLiveData.observe(this, Observer {
            ToastUtil.instance.showMsg(it[0].username)
        })
    }

    override fun init() {
        val list  =  WanAndroidDataBase.get(mContext).userInfoDao().getAll()
        if (list.isNotEmpty()){
            val bean = list[0]
            Glide.with(mContext).load(bean).placeholder(R.drawable.icon_avatar).into(mBinding.image)
            mBinding.tvName.text = bean.nickname
        }
    }

    override fun getLayoutId() = R.layout.fragment_mine
}