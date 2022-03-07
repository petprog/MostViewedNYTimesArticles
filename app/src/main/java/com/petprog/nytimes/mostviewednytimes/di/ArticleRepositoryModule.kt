package  com.petprog.nytimes.mostviewednytimes.di

import com.petprog.nytimes.mostviewednytimes.data.repository.ArticleRepository
import com.petprog.nytimes.mostviewednytimes.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Currently PostRepository is only used in ViewModels.
 * PostDetailsViewModel is not injected using @HiltViewModel so can't install in ViewModelComponent.
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ArticleRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindPostRepository(repository: ArticleRepositoryImpl): ArticleRepository
}
