package com.example.mealzapp.di

import com.example.domain.entitey.CategoriesResponse
import com.example.domain.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    suspend fun provideGetMealzUseCase(mealzRepo: MealsRepo): CategoriesResponse =
        mealzRepo.getMealsFromRemote()

}