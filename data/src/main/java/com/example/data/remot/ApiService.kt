package com.example.data.remot

import com.example.domain.entity.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {


    @Headers("Accept-Version: v1", "Authorization: Client-ID DMsgIHVHA6yjRE467YdSoiJqz7wEZKamHKMerRbk85M")
    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int): UnsplashResponse


}