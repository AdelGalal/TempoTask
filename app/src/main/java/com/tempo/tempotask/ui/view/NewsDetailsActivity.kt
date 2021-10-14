package com.tempo.tempotask.ui.view

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.tempo.tempotask.R
import com.tempo.tempotask.data.model.Articles
import com.tempo.tempotask.databinding.ActivityNewsDetailsBinding
import com.tempo.tempotask.utils.DateUtil


class NewsDetailsActivity : AppCompatActivity() {
    companion object {
        val ARTICLES = "articles"
    }

    private lateinit var binding: ActivityNewsDetailsBinding
    lateinit var articles: Articles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        if (intent != null) articles = intent.getParcelableExtra(ARTICLES)!!
        setUI()
    }

    private fun setUI() {
        articles.publishedAt= DateUtil.getSimpleDateFormate(articles.publishedAt!!)
        binding.articleDetails = articles
        binding.source = articles.source
        binding.tvSource.paintFlags = binding.tvSource.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvSource.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(articles.url))
            startActivity(intent)
        }
        Glide.with(binding.imageViewItem.context)
            .load(articles.urlToImage)
            .into(binding.imageViewItem)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}