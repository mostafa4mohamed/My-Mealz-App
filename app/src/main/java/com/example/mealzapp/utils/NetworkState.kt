package com.example.mealzapp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext

sealed class NetworkState {

    //idle
    object Idle : NetworkState()

    //loading
    object Loading : NetworkState()

    //result
    data class Result<T>(var response: T) : NetworkState()

    //error
    data class Error(var errorCode: Int, var msg: String? = null) : NetworkState() {

        fun handleErrors(
            @ApplicationContext
            mContext: Context,
        ) {

            Log.e(TAG, "handleErrors: msg $msg")
            Log.e(TAG, "handleErrors: error code $errorCode")

            Toast.makeText(mContext, msg ?: "known_error", Toast.LENGTH_SHORT).show()
        }

        companion object {
            private val TAG = this::class.java.name
        }

    }

}
