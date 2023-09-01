package com.wanandroid.xp_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dycw.xp_project.bean.p
import com.wanandroid.xp_project.R
import java.util.ArrayList

/**
 * create time : 2023/9/1 11:22
 * create by : xupengpeng
 */
interface ProjectItemClickListener {
    fun onItemClick(position: Int)
    fun onCollectClick(position: Int)
}

class ProjectRvAdapter(var listener: ProjectItemClickListener) : RecyclerView.Adapter<ProjectRvAdapter.ViewHolder>(){

    private var diff: AsyncListDiffer<p>

    init {
        diff = AsyncListDiffer(this,MyCallBack())
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project_child,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = diff.currentList[position]
        Glide.with(holder.itemView.context).load(data.envelopePic).into(holder.image)
        holder.tvDesc.text = data.desc
        holder.tvTitle.text = data.title
        holder.tvName.text = data.author
        holder.tvTime.text = data.niceDate
        holder.itemView.tag = position

        if (data.collect) {
            holder.ivCollect.setImageResource(R.drawable.icon_collect_2)
        } else {
            holder.ivCollect.setImageResource(R.drawable.icon_collect_1)
        }

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }

        holder.ivCollect.setOnClickListener {
            listener.onCollectClick(position)
        }

    }

    fun setData(list: List<p>?) {
        //AsyncListDiffer需要一个新数据，不然添加无效
        diff.submitList(if (list != null) ArrayList(list) else null)
    }

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView){
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val image: ImageView = itemView.findViewById(R.id.image)
        val ivCollect: ImageView = itemView.findViewById(R.id.ivCollect)
    }

    class MyCallBack : DiffUtil.ItemCallback<p>() {
        override fun areItemsTheSame(oldItem: p, newItem: p): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: p, newItem: p): Boolean {
            return oldItem.title == newItem.title
        }

    }

}

