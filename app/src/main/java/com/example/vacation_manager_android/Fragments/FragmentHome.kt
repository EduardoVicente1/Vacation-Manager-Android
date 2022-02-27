package com.example.vacation_manager_android.Fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.ApiEndpoints
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import com.example.vacation_manager_android.adapters.WorkersHabilitadosAdapter
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import com.paulocabelloacha.sendnotif.SendNotifFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var activityParent: Activity
    lateinit var fragpaulo : Fragment

    lateinit var recyclerHabilitados: RecyclerView
    lateinit var workersHabilitadosAdapter: WorkersHabilitadosAdapter

    lateinit var retroFitConnection: ApiEndpoints
    private var workersList: WorkersGetResponse? = null
    private var filteredWorkersList: List<WorkersGetResponse.Data?>? = null
    private var cache: MutableList<WorkersGetResponse.Data?>? = null
//    lateinit var teamcolors: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityParent = activity as HostActivity
        recyclerHabilitados = view.findViewById(R.id.recycler_container_habilitados)
        retroFitConnection = RetrofitClient.getInstance()
        retroFitConnection.getAllWorkers().enqueue(

            object : Callback<WorkersGetResponse> {
                override fun onResponse(
                    call: Call<WorkersGetResponse>,
                    response: Response<WorkersGetResponse>,
                ) {
                    Log.d("RESPONSE", response.body().toString())


                    if (response.body() != null) {
                        workersList = response.body()

                        val ahora = LocalDate.now()
                        val fmt: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                        workersList?.data?.forEach {
                            var fecha = it?.attributes?.startDate?.split("-")
                            var workDate = LocalDate.parse("${fecha?.get(2)}-${fecha?.get(0)}-${fecha?.get(1)}", fmt)
                            var period = Period.between(workDate, ahora)
                            if (period.years == 0) {

                                if (period.months >= 11) {
                                    cache?.add(it)
                                }
                            } else if (period.years > 0)
                                cache?.add(it)
                        }
//                        filteredWorkersList = cache?.filter {
//                            it?.attributes?.emailSender == false
//                        }

                        Log.d("response", filteredWorkersList.toString())
                        workersHabilitadosAdapter = WorkersHabilitadosAdapter(workersList?.data){
                            workersData -> fragpaulo=SendNotifFragment.newInstance(workersData,"")
                        }
                        recyclerHabilitados.layoutManager =
                            LinearLayoutManager(activityParent, LinearLayoutManager.VERTICAL, false)
                        recyclerHabilitados.adapter = workersHabilitadosAdapter
                    }
                }

                override fun onFailure(call: Call<WorkersGetResponse>, t: Throwable) {
                    Log.d("Error", t.toString())
                }
            }
        )*/
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