package com.example.vacation_manager_android.Fragments

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.vacation_manager_android.Retrofit.RetrofitCallback
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class CustomBaseFragment : Fragment() {
    fun retrofitRequestManager(apiCall : String, data : Any, onSuccessCallback : () -> Unit){
        var retroFitInstance = RetrofitClient.getInstance()
        when (apiCall) {
            "getPendingWorkers" -> {
                retroFitInstance.getPendingWorkers().enqueue(
                    object : RetrofitCallback<Any>() {
                        override fun onResponse(request: Call<Any>, response: Response<Any>) {
                            super.onResponse(request, response)
                            if (response.isSuccessful){
                                Log.d("RetrofitOnSuccess", "All workers retrieved with success")
                                onSuccessCallback()
                            }
                        }
                    }
                )
            }
        }
    }
}