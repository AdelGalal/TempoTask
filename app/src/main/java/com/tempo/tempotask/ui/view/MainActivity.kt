package com.tempo.tempotask.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tempo.tempotask.R
import com.tempo.tempotask.data.model.Articles
import com.tempo.tempotask.data.model.News
import com.tempo.tempotask.databinding.ActivityMainBinding
import com.tempo.tempotask.ui.adapter.NewsAdapter
import com.tempo.tempotask.ui.viewmodel.MainViewModel
import com.tempo.tempotask.utils.Resource
import com.tempo.tempotask.utils.Status
import com.tempo.tempotask.utils.hideKeyboard
import com.tempo.tempotask.utils.searchQuery
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), NewsAdapter.ItemClickListener {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNewsUI()
        setupObserver()
    }

    private fun setupNewsUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(arrayListOf(), this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = newsAdapter
        binding.ivSearch.setOnClickListener({ filterBySearch() })
    }

    private fun setupObserver() {
        mainViewModel.fetchNews(searchQuery)
        mainViewModel.news.observe(this, Observer { setNews(it) })
    }

    private fun filterBySearch() {
        hideKeyboard(binding.etSearch)
        if (binding.etSearch.text.toString().length >= 3) {
            mainViewModel.fetchNews(binding.etSearch.text.toString())
        } else
            Toast.makeText(this, getString(R.string.filter_msg), Toast.LENGTH_SHORT).show()
    }

    fun setNews(news: Resource<News>) {
        binding.progressBar.visibility = View.GONE
        when (news.status) {

            Status.SUCCESS -> {
                news.data?.let { news -> renderNewsList(news) }
                binding.recyclerView.visibility = View.VISIBLE
            }
            Status.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
            Status.ERROR -> {
                //Handle Error
                Toast.makeText(this, getString(R.string.connection_error), Toast.LENGTH_LONG).show()
            }
            Status.NOINTERNETCONNECTION -> {
                //Handle Error
                Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun renderNewsList(news: News) {
        newsAdapter.addData(news.articles)
        newsAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(article: Articles) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.apply {
            putExtra(NewsDetailsActivity.ARTICLES, article)
        }
        startActivity(intent)
    }
}