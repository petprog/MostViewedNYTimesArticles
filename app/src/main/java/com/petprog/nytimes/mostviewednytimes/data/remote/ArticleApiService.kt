package com.petprog.nytimes.mostviewednytimes.data.remote

import com.petprog.nytimes.mostviewednytimes.BuildConfig
import com.petprog.nytimes.mostviewednytimes.data.repository.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApiService {

    @GET("/svc/mostpopular/v2/viewed/{period}.json")
    suspend fun getArticles(@Path("period") period: Int = 7,
                            @Query("api-key") apiKey: String = BuildConfig.API_KEY): Response<ArticlesResponse>
}