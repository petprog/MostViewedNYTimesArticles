package  com.petprog.nytimes.mostviewednytimes.di


import com.petprog.nytimes.mostviewednytimes.BuildConfig
import com.petprog.nytimes.mostviewednytimes.data.remote.ArticleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ArticlesApiModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): ArticleApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(ArticleApiService::class.java)
}
