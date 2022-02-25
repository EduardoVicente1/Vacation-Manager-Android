package com.example.vacation_manager_android.data_classes

import retrofit2.Call
import retrofit2.http.*

interface DbService {

    @GET(value = "api/workers-inf/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUserById(@Path(value = "id") id: Int): Call<WorkersGetResponse>

    @PUT(value = "api/workers-inf/{id}")
    @Headers("Accept:application/json",
        "Content-Type:application/json")
    fun putWorker(@Path(value = "id") id: Int, @Body params: WorkersPutRequest): Call<Any>
}