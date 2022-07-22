package com.petprog.nytimes.mostviewednytimes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petprog.nytimes.mostviewednytimes.data.repository.ArticleRepository
import com.petprog.nytimes.mostviewednytimes.model.Article
import com.petprog.nytimes.mostviewednytimes.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [ArticleListFragment]
 */
@HiltViewModel
class ArticleListViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {

    private val _articles: MutableStateFlow<State<List<Article>>> =
        MutableStateFlow(State.loading())

    val articles: StateFlow<State<List<Article>>> = _articles

    fun getArticles() {
        viewModelScope.launch {
            setToLoadAgain()
            articleRepository.getAllArticles()
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _articles.value = state }
        }
    }
    fun setToLoadAgain() {
        _articles.value = State.loading()
    }
}
