package com.example.data.repo

import com.example.data.remote.ApiServices
import com.example.domain.entitey.CategoriesResponse
import com.example.domain.repo.MealsRepo

class MealzRepoImpl (private val apiServices: ApiServices) : MealsRepo {
    override suspend fun getMealsFromRemote(): CategoriesResponse = apiServices.getMealz()
}