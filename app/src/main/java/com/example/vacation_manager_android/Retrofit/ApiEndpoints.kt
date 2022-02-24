package com.example.vacation_manager_android.Retrofit

import com.example.vacation_manager_android.data_classes.WorkerPutRequest
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoints {
    @GET("/api/workers-inf/")
    fun getAllWorkers(): Call<WorkersGetResponse>

    @PUT("/api/workers-inf/{id}")
    fun updateWorker(@Path("id") id: String, @Body newWorkerData : WorkerPutRequest): Call<Any>

}