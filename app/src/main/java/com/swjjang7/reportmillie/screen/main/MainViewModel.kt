package com.swjjang7.reportmillie.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swjjang7.reportmillie.repository.remote.NetworkImpl
import com.swjjang7.reportmillie.repository.remote.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkImpl: NetworkImpl
) : ViewModel() {
    private val _newsList = MutableStateFlow<List<Article>>(listOf())
    val newsList: StateFlow<List<Article>> = _newsList

    init {
        requestNewsList()
    }

    private fun requestNewsList() {
        viewModelScope.launch {
            _newsList.value = networkImpl.getNewsList()
        }
    }
}