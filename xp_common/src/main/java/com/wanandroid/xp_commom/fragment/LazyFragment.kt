package com.wanandroid.xp_commom.fragment

import androidx.fragment.app.Fragment

/**
 * created time : 2023/8/29 11:00
 * created by :  xupengpeng
 */
abstract class LazyFragment : Fragment(){

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}