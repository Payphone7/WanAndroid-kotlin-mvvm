package com.wanandroid.xp_mine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wanandroid.xp_mine.R
import com.wanandroid.xp_mine.bean.MineMenu

/**
 * create time : 2023/9/6 10:31
 * create by : xupengpeng
 */
class MineItemAdapter : RecyclerView.Adapter<MineItemAdapter.ViewHolder>(){

    private var mList:List<MineMenu> = arrayListOf()

    fun setData(list: List<MineMenu>){
        mList = list
        notifyDataSetChanged()
    }
    class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mine,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = mList[position]
        Glide.with(holder.itemView.context).load(bean.res).into(holder.image)
        holder.tvName.text = bean.name
    }
}