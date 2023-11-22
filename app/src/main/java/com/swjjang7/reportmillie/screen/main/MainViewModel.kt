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
            _newsList.value = try {
                // 예제 앱이며, 간단한 처리만 할 것임으로 try catch 사용
                networkImpl.getNewsList()
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO : 로컬 데이터 노출
                listOf<Article>()
            }
        }
    }
}