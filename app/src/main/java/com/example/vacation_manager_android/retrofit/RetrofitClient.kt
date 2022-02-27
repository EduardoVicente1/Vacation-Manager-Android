package com.example.vacation_manager_android.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private val BASE_URL = "https://vacation-manager-py.herokuapp.com"
        private var INSTANCE: ApiEndpoints? = null

        @Synchronized
        fun getInstance(): ApiEndpoints {
            if (INSTANCE == null)
                INSTANCE = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiEndpoints::class.java)
            return INSTANCE as ApiEndpoints
        }
    }
}