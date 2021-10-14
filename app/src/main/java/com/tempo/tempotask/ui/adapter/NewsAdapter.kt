package com.tempo.tempotask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tempo.tempotask.R
import com.tempo.tempotask.data.model.Articles
import com.tempo.tempotask.databinding.ItemLayoutBinding

class NewsAdapter(private var articleNews: List<Articles>,private val itemClickListener: ItemClickListener )
    : RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {
    lateinit var binding: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = articleNews.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(articleNews[position])
        holder.itemView.setOnClickListener {itemClickListener.onItemClick(articleNews[position])}
    }

    fun addData(list: List<Articles>?) {
        if (list != null) {
            articleNews=list
        }
    }
    class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Articles) {
            binding.item = article

            Glide.with(binding.imageViewAvatar.context)
                .load(article.urlToImage)
                .into(binding.imageViewAvatar)

        }
    }

    interface ItemClickListener
    {
        fun onItemClick(article: Articles)
    }
}