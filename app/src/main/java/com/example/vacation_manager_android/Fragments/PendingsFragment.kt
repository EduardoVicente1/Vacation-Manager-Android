package com.example.vacation_manager_android.Fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.ApiEndpoints
import com.example.vacation_manager_android.Retrofit.RetrofitCallback
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import com.example.vacation_manager_android.adapters.PendingWorkersAdapter
import com.example.vacation_manager_android.data_classes.WorkerPutRequest
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PendingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PendingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var activityParent : Activity

    lateinit var pendingWorkersAdapter : PendingWorkersAdapter
    lateinit var recyclerVariable : RecyclerView

    lateinit var retroFitInstance : ApiEndpoints
    private var filteredWorkersList : List<WorkersGetResponse.Data?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pendings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityParent = activity as HostActivity

        retroFitInstance = RetrofitClient.getInstance()
        retroFitInstance.getPendingWorkers().enqueue(

            object : RetrofitCallback<Any>() {
                override fun onResponse(request: Call<Any>, response: Response<Any>) {
                    super.onResponse(request, response)
                    if (response.isSuccessful){
                        Log.d("RetrofitOnSuccess", "All workers retrieved with success")
                        val allWorkersList : WorkersGetResponse = response.body() as WorkersGetResponse

                        filteredWorkersList = allWorkersList.data?.filter {
                            it?.attributes?.emailSended == false
                        }

                        pendingWorkersAdapter = PendingWorkersAdapter(filteredWorkersList){
                            workerData, action ->
                            when(action){
                                "accept" -> acceptVacation(workerData)
                                "reject" -> rejectVacation(workerData)
                                "edit" -> editVacation(workerData)
                            }
                        }

                        recyclerVariable = view.findViewById(R.id.recycler_container)
                        recyclerVariable.layoutManager = LinearLayoutManager(activityParent, LinearLayoutManager.VERTICAL, false)
                        recyclerVariable.adapter = pendingWorkersAdapter
                    }
                }
            }
        )

    }

    private fun editVacation(workerData: WorkersGetResponse.Data?) {
        Log.d("Button", "editVacation")
        activity?.supportFragmentManager?.popBackStack()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.contiene_Fragments, SetDateFragment.newInstance(workerData))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun rejectVacation(workerData: WorkersGetResponse.Data?) {
        var newWorkerData = WorkerPutRequest(
            WorkerPutRequest.Data(
                workerData?.attributes?.emailSended,
                workerData?.attributes?.endDate,
                false,
                workerData?.attributes?.startDate,
                workerData?.attributes?.workMail,
                workerData?.attributes?.workTeam,
                workerData?.attributes?.workerName
            )
        )

        retroFitInstance = RetrofitClient.getInstance()
        retroFitInstance.updateWorker(workerData?.id.toString(), newWorkerData).enqueue(
            object : RetrofitCallback<Any>() {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    super.onResponse(call, response)
                    if(response.isSuccessful){
                        Log.d("RetrofitOnSuccess", "vacation rejected for worker: "+workerData?.attributes?.workerName.toString())
                    }
                }
            }
        )
    }

    private fun acceptVacation(workerData: WorkersGetResponse.Data?) {
        workerData?.attributes?.onVacation = true
        var newWorkerData = WorkerPutRequest(
            WorkerPutRequest.Data(
                workerData?.attributes?.emailSended,
                workerData?.attributes?.endDate,
                workerData?.attributes?.onVacation,
                workerData?.attributes?.startDate,
                workerData?.attributes?.workMail,
                workerData?.attributes?.workTeam,
                workerData?.attributes?.workerName
            )
        )

        retroFitInstance = RetrofitClient.getInstance()
        retroFitInstance.updateWorker(workerData?.id.toString(), newWorkerData).enqueue(
            object : RetrofitCallback<Any>() {
                override fun onResponse(request: Call<Any>, response: Response<Any>) {
                    super.onResponse(request, response)
                    if(response.isSuccessful){
                        Log.d("RetrofitOnSuccess", "vacation accepted for worker: "+workerData?.attributes?.workerName.toString())
                        activity?.supportFragmentManager?.popBackStack()
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.contiene_Fragments, SetDateFragment.newInstance(workerData))
                            ?.addToBackStack(null)
                            ?.commit()
                    }
                }
            }
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PendingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PendingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}