package com.tempo.tempotask.data.repository

import com.tempo.tempotask.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getNews(
        searchQuery: String?,
        date: String?,
        pageSize:String?,
        order: String?,
        apiKey: String?
    ) = apiHelper.getNews(searchQuery, date, pageSize,order, apiKey)

}