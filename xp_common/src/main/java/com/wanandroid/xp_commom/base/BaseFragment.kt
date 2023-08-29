package com.wanandroid.xp_commom.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wanandroid.xp_commom.fragment.LazyFragment

/**
 * Author by xupengpeng
 * Date : 2023/8/29 10:59
 */

abstract class BaseFragment <DB : ViewDataBinding> : LazyFragment(),AbsInterface{

    val TAG :String = this.javaClass.simpleName

    lateinit var mBinding: DB

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observer()
    }


}