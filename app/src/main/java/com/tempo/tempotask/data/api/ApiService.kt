package com.tempo.tempotask.data.api

import com.tempo.tempotask.data.model.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNews(@Query("q") searchQuery:String?, @Query("date")  date:String?,@Query("pagesize")  pageSize:String?, @Query("order") order:String?, @Query("apiKey") apiKey:String?):News?
}