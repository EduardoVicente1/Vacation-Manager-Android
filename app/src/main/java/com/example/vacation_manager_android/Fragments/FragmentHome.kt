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
import com.example.vacation_manager_android.Adapters.CalendarWorkersAdapter
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.ApiEndpoints
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var activityParent : Activity

    lateinit var calendarWorkersAdapter: CalendarWorkersAdapter
    lateinit var recyclerVariable : RecyclerView

    lateinit var retroFitConnection : ApiEndpoints
    private var workersList: WorkersGetResponse?= null
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityParent = activity as HostActivity

        retroFitConnection = RetrofitClient.getInstance()
        retroFitConnection.getAllWorkers().enqueue(

            object : Callback<WorkersGetResponse> {
                override fun onResponse(call: Call<WorkersGetResponse>, response: Response<WorkersGetResponse>) {
                    Log.d("RESPONSE", response.body().toString())

                    if(response.body() != null) {
                        workersList = response.body()

                        filteredWorkersList = workersList?.data?.filter {
                            it?.attributes?.onVacation == true
                        }
                        Log.d("FILTER", filteredWorkersList.toString())
                    }

                    calendarWorkersAdapter = CalendarWorkersAdapter(filteredWorkersList)
                    recyclerVariable = view.findViewById(R.id.recycler_calendar_worker_container)
                    recyclerVariable.layoutManager = LinearLayoutManager(activityParent, LinearLayoutManager.VERTICAL, false)
                    recyclerVariable.adapter = calendarWorkersAdapter
                }
                override fun onFailure(call: Call<WorkersGetResponse>, t: Throwable) {
                    Log.d("Error", t.toString())
                }
            }
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}