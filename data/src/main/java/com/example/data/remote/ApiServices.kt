package com.example.data.remote

import com.example.domain.entitey.CategoriesResponse
import retrofit2.http.GET

interface ApiServices {

    @GET("categories.php")
    suspend fun getMealz():CategoriesResponse

}