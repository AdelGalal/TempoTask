package com.tempo.tempotask.data.api

import com.tempo.tempotask.data.model.News

interface ApiHelper {
    suspend fun getNews( searchQuery:String? ,  date:String?, pageSize:String? ,order:String? , apiKey:String? ): News?
}