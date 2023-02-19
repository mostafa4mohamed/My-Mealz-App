package com.example.domain.repo

import com.example.domain.entitey.CategoriesResponse

interface MealsRepo {
    suspend fun getMealsFromRemote(): CategoriesResponse
}