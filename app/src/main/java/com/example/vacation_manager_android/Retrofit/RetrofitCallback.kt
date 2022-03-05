package com.example.vacation_manager_android.Retrofit

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "Retrofit_Fail"

open class RetrofitCallback<T> : Callback<Any>{

    override fun onResponse(request: Call<Any>, response: Response<Any>,) {
        if (response.body() == null || response.code() == 404) {
            Log.d(TAG, "response is null")
        } else {
            when (response.code().toString()) {
                "3333" -> {
                    Log.d(TAG, "session timed out")
                }
                "1111" -> {
                    Log.d(TAG, "failed")
                }
            }
        }
    }

    override fun onFailure(request: Call<Any>, t: Throwable) {
        if (t.message != null) {
            Log.e(TAG, t.message!!)}
    }
}