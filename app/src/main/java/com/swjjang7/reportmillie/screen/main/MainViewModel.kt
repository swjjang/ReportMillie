package com.swjjang7.reportmillie.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swjjang7.reportmillie.repository.ArticleImpl
import com.swjjang7.reportmillie.repository.local.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articleImpl: ArticleImpl
) : ViewModel() {
    private val _newsList = MutableStateFlow<List<Article>>(listOf())
    val newsList: StateFlow<List<Article>> = _newsList

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    init {
        requestNewsList()
    }

    private fun requestNewsList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _newsList.value = articleImpl.getNewsList()
            }
        }
    }

    fun onItemClick(item: Article) {
        viewModelScope.launch {
            _event.emit(Event.Click(item))
        }
    }
}

sealed class Event() {
    data class Click(val item: Article) : Event()
    data object None : Event()
}