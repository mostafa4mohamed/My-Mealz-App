package com.example.mealzapp.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.utils.Constants
import com.example.mealzapp.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() :
    ViewModel() {

    fun <T> runApi(
        _apiStateFlow: MutableStateFlow<NetworkState>,
        block: T
    ) {

        _apiStateFlow.value = NetworkState.Loading
        try {
//            if (Utils.isInternetAvailable())
                viewModelScope.launch {

                    kotlin.runCatching {
                        block
                    }.onFailure {

                        Log.e(TAG, "runApi: 3")
                        when (it) {
                            is java.net.UnknownHostException ->
                                _apiStateFlow.value =
                                    NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
                            is java.net.ConnectException ->
                                _apiStateFlow.value =
                                    NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
                            else -> _apiStateFlow.value =
                                NetworkState.Error(Constants.Codes.UNKNOWN_CODE)
                        }

                    }.onSuccess {

                        Log.e(TAG, "runApi: 4")
                        try {
                            _apiStateFlow.value = NetworkState.Result(it)
                        }catch (e:Exception){
                            Log.e(TAG, "runApi: $it")
                            _apiStateFlow.value = NetworkState.Error(Constants.Codes.UNKNOWN_CODE)
                        }
                    }

                }
//            else
//                _apiStateFlow.value = NetworkState.Error(Constants.Codes.EXCEPTIONS_CODE)
        } catch (e: Exception) {
            Log.e(TAG, "runApi: ${e.message}")
        }


    }

    companion object {
        private const val TAG = "BaseViewModel"
    }

}