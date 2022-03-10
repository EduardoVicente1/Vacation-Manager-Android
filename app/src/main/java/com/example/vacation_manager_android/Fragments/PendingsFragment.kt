package com.example.vacation_manager_android.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.RetrofitManager
import com.example.vacation_manager_android.adapters.PendingWorkersAdapter
import com.example.vacation_manager_android.data_classes.WorkerPutRequest
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

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

    var retrofitManager = RetrofitManager()

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

        retrofitManager.retrofitRequestBuilder("getPendingWorkers", mapOf()){
            response ->
            var pendingWorkersList = response as WorkersGetResponse

            pendingWorkersAdapter = PendingWorkersAdapter(pendingWorkersList.data){
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

    private fun editVacation(workerData: WorkersGetResponse.Data?) {
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

        var apiRequestArguments = buildMap {
            put("workerId", workerData?.id.toString())
            put("newWorkerData", newWorkerData)
        }

        retrofitManager.retrofitRequestBuilder("updateWorker", apiRequestArguments){
            Toast.makeText(activityParent, "Vacacion rechazada", Toast.LENGTH_SHORT).show()
        }
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

        var apiRequestArguments = buildMap {
            put("workerId", workerData?.id.toString())
            put("newWorkerData", newWorkerData)
        }

        retrofitManager.retrofitRequestBuilder("updateWorker", apiRequestArguments){
            activity?.supportFragmentManager?.popBackStack()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.contiene_Fragments, SetDateFragment.newInstance(workerData))
                ?.addToBackStack(null)
                ?.commit()
        }
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