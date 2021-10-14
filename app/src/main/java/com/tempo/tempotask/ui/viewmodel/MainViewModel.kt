package com.tempo.tempotask.ui.viewmodel

import androidx.lifecycle.*

import com.tempo.tempotask.data.model.News
import com.tempo.tempotask.data.repository.MainRepository
import com.tempo.tempotask.utils.*
import kotlinx.coroutines.launch


class MainViewModel(val mainRepository: MainRepository, val networkHelper: NetworkHelper) :
    ViewModel() {


    private val _news = MutableLiveData<Resource<News>>()

    val news: LiveData<Resource<News>>
        get() = _news

    fun fetchNews(searchFilter: String) {
        viewModelScope.launch {
            _news.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {

                mainRepository.getNews(searchFilter, date, pageSize, order, APIKEY).let {
                    if (it!!.status == OKSTATUS) {
                        _news.postValue(Resource.success(it))
                    } else _news.postValue(Resource.error("", null))
                }
            } else _news.postValue(Resource.noInternetConnection("", null))
        }
    }
}