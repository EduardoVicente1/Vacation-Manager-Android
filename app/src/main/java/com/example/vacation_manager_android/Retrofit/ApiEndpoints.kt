package com.example.vacation_manager_android.Retrofit

import com.example.vacation_manager_android.data_classes.UserGetResponse
import com.example.vacation_manager_android.data_classes.UserInfoRegister
import com.example.vacation_manager_android.data_classes.WorkerPutRequest
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoints {
    @GET("/api/workers-inf/?pagination[page]=1&pagination[pageSize]=100&sort[0]=on_vacation%3Adesc")
    fun getAllWorkers(): Call<WorkersGetResponse>

    @GET("/api/users-backups/")
    fun getUser(): Call<UserGetResponse>

    @POST("/api/users-backups/")
    fun addUser(@Body userData: UserInfoRegister): Call<UserInfoRegister>

    @PUT("/api/workers-inf/{id}")
    fun updateWorker(@Path("id") id: String, @Body newWorkerData : WorkerPutRequest): Call<Any>

    @POST("/api/workers-inf/")
    fun newWorker(@Body workerData: WorkerPutRequest): Call<WorkerPutRequest>

    @GET("/api/workers-inf/?pagination[page]=1&pagination[pageSize]=100&sort[0]=email_sended%3Adesc")
    fun getPendingWorkers(): Call<Any>


}