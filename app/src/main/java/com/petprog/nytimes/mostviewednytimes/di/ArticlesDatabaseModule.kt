package  com.petprog.nytimes.mostviewednytimes.di
import android.app.Application
import com.petprog.nytimes.mostviewednytimes.data.local.ArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ArticlesDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = ArticlesDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: ArticlesDatabase) = database.getArticlesDao()
}
