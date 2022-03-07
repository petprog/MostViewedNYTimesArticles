package com.petprog.nytimes.mostviewednytimes.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.petprog.nytimes.mostviewednytimes.data.repository.ArticleRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * ViewModel for [ArticleDetailsFragment]
 */
@ExperimentalCoroutinesApi
class ArticleDetailsViewModel @AssistedInject constructor(
    articleRepository: ArticleRepository,
    @Assisted articleId: Long
) : ViewModel() {

    val article = articleRepository.getArticleById(articleId).asLiveData()

    @AssistedFactory
    interface ArticleDetailsViewModelFactory {
        fun create(articleId: Long): ArticleDetailsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: ArticleDetailsViewModelFactory,
            articleId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(articleId) as T
            }
        }
    }
}
