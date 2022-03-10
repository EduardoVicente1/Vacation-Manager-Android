package com.example.vacation_manager_android.Retrofit

import android.content.ContentValues.TAG
import android.util.Log
import com.example.vacation_manager_android.data_classes.WorkerPutRequest
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "Retrofit_Fail"

class RetrofitManager {
    fun retrofitRequestBuilder(apiRequest : String, apiRequestArguments : Map<String, Any>, onSuccessCallback : (response : Any?) -> Unit){
        var retroFitInstance = RetrofitClient.getInstance()
        when (apiRequest) {
            "getPendingWorkers" -> {
                retroFitInstance.getPendingWorkers().enqueue(
                    object : Callback<WorkersGetResponse> {
                        override fun onResponse(request: Call<WorkersGetResponse>, response: Response<WorkersGetResponse>) {
                            if (response.isSuccessful){
                                Log.d("RetrofitOnSuccess", "Pending workers retrieved with success")
                                onSuccessCallback(response.body())
                            }
                        }

                        override fun onFailure(call: Call<WorkersGetResponse>, t: Throwable) {
                            if (t.message != null) {
                                Log.e(com.example.vacation_manager_android.Retrofit.TAG, t.message!!)
                            }
                        }
                    }
                )
            }
            "updateWorker" -> {
                var workerId = apiRequestArguments["workerId"].toString()
                var newWorkerData = apiRequestArguments["newWorkerData"] as WorkerPutRequest
                retroFitInstance.updateWorker(workerId, newWorkerData).enqueue(
                    object : Callback<WorkerPutRequest> {
                        override fun onResponse(call: Call<WorkerPutRequest>, response: Response<WorkerPutRequest>) {
                            if(response.isSuccessful){
                                Log.d("RetrofitOnSuccess", "Worker data edited successfully: "+newWorkerData.data?.workerName.toString())
                                onSuccessCallback(response.body())
                            }
                        }

                        override fun onFailure(call: Call<WorkerPutRequest>, t: Throwable) {
                            if (t.message != null) {
                                Log.e(com.example.vacation_manager_android.Retrofit.TAG, t.message!!)
                            }
                        }
                    }
                )
            }
        }
    }
}