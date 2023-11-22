package com.swjjang7.reportmillie.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swjjang7.reportmillie.repository.ArticleImpl
import com.swjjang7.reportmillie.repository.local.entity.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articleImpl: ArticleImpl
) : ViewModel() {
    private val _newsList = MutableStateFlow<List<Article>>(listOf())
    val newsList: StateFlow<List<Article>> = _newsList

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    private var isLoading: AtomicBoolean = AtomicBoolean(false)

    init {
        viewModelScope.launch {
            articleImpl.findAll().collect { list ->
                _newsList.emit(list)
            }
        }

        requestNewsList()
    }

    fun requestNewsList() {
        if (isLoading.getAndSet(true)) {
            return
        }

        viewModelScope.launch {
            articleImpl.getNewsList()
            isLoading.lazySet(false)
        }
    }

    fun onItemClick(item: Article) {
        viewModelScope.launch {
            _event.emit(Event.Click(item))
            articleImpl.updateArticle(item)
        }
    }

    fun onRetryClick() {
        viewModelScope.launch {
            _event.emit(Event.Retry)
        }
    }

    fun onMoveNetworkSettingClick() {
        viewModelScope.launch {
            _event.emit(Event.MoveNetworkSetting)
        }
    }
}

sealed class Event() {
    data class Click(val item: Article) : Event()
    data object Retry : Event()
    data object MoveNetworkSetting : Event()
    data object None : Event()
}