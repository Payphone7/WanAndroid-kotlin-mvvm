package com.wanandroid.xp_home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dycw.xp_home.bean.BannerBean
import com.wanandroid.xp_home.R
import com.youth.banner.adapter.BannerAdapter

/**
 * create time : 2023/8/30 18:03
 * create by : xupengpeng
 */
class HomeBannerAdapter(mDatas: List<BannerBean>?) : BannerAdapter<BannerBean?, HomeBannerAdapter.ViewHolder>(mDatas) {
    //更新数据
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<BannerBean>) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear()
        mDatas.addAll(data)
        notifyDataSetChanged()
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner,parent,false)
        return ViewHolder(view)
    }

    override fun onBindView(holder: ViewHolder?, data: BannerBean?, position: Int, size: Int) {
        if (mDatas.isNullOrEmpty()){
            return
        }
        val bannerBean = mDatas[position]
        holder?.apply {
            Glide.with(this.itemView.context).load(bannerBean?.imagePath).into(holder.imageView)
        }

    }



    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.image)
    }
}