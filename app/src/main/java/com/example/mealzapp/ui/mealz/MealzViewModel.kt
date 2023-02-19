package com.example.mealzapp.ui.mealz

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetMealzUseCase
import com.example.mealzapp.base.BaseViewModel
import com.example.mealzapp.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealzViewModel @Inject constructor(private val getMealzUseCase: GetMealzUseCase) :
    BaseViewModel() {

    private val _getMealzStateFlow = MutableStateFlow<NetworkState>(NetworkState.Idle)
    val getMealzStateFlow get() = _getMealzStateFlow

    fun getMealz() {

        Log.e(TAG, "getMealz: vm")
        viewModelScope.launch {
            runApi(_getMealzStateFlow, getMealzUseCase.invoke())
        }
    }

    companion object{
        private const val TAG = "MealzViewModel"
    }

}