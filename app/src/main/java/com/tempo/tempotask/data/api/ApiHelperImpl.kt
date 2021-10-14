package com.tempo.tempotask.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getNews(
        searchQuery: String?,
        date: String?,
        pageSize: String?,
        order: String?,
        apiKey: String?
    ) = apiService.getNews(searchQuery, date, pageSize,order, apiKey)

}