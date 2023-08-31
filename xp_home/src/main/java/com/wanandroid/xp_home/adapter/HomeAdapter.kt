package com.wanandroid.xp_home.adapter

import android.content.Context
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dycw.xp_home.bean.ArticleBean
import com.wanandroid.xp_home.R


class HomeAdapter(var listener: HomeItemClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var diff: AsyncListDiffer<ArticleBean.ArticleDetail>

    init {
        diff = AsyncListDiffer(this, MyCallback())
    }

    fun setData(list: List<ArticleBean.ArticleDetail>?) {
        //AsyncListDiffer需要一个新数据，不然添加无效
        diff.submitList(if (list != null) ArrayList(list) else null)
        notifyDataSetChanged()
    }

    class MyCallback : DiffUtil.ItemCallback<ArticleBean.ArticleDetail>() {
        override fun areItemsTheSame(
            oldItem: ArticleBean.ArticleDetail,
            newItem: ArticleBean.ArticleDetail
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ArticleBean.ArticleDetail,
            newItem: ArticleBean.ArticleDetail
        ): Boolean {
            return oldItem.title == newItem.title && oldItem.niceDate == newItem.niceDate
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var name: TextView = itemView.findViewById(R.id.name)
        var time: TextView = itemView.findViewById(R.id.time)
        var type: TextView = itemView.findViewById(R.id.type)
        var tag1: TextView = itemView.findViewById(R.id.tag1)
        var tag2: TextView = itemView.findViewById(R.id.tag2)
        var collect: ImageView = itemView.findViewById(R.id.img_collect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (diff.currentList.size == 0) 0 else diff.currentList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (diff.currentList.size == 0){
            return
        }
        val data = diff.currentList[position]
        holder.title.text = data.title
        holder.time.text = data.niceDate
        holder.type.text = data.superChapterName
        holder.tag1.visibility = if (data.fresh) View.VISIBLE else View.GONE
        holder.tag2.visibility = if (data.superChapterId == 408) View.VISIBLE else View.GONE
        holder.name.text = data.author.ifEmpty { data.shareUser }

        if (data.collect) {
            holder.collect.setImageResource(R.drawable.icon_collect_2)
        } else {
            holder.collect.setImageResource(R.drawable.icon_collect_1)
        }


        holder.itemView.setOnClickListener{
            this.listener.onItemClick(position)
        }

        holder.collect.setOnClickListener{
            this.listener.onCollectClick(position)
        }
    }
}
