package com.example.vacation_manager_android.Retrofit

import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("/api/workers-inf/?pagination[page]=1&pagination[pageSize]=100&sort[0]=email_sended%3Aasc")
    fun getAllWorkers(): Call<WorkersGetResponse>

}