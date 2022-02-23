package com.example.vacation_manager_android.Fragments

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
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import com.example.vacation_manager_android.adapters.PendingWorkersAdapter
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

    lateinit var retroFitConnection : ApiEndpoints
    private var workersList: WorkersGetResponse?= null

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

        var activityParent = activity as HostActivity

        lateinit var pokemonAdapter : PendingWorkersAdapter
        lateinit var recyclerVariable : RecyclerView

        retroFitConnection = RetrofitClient.getInstance()
        retroFitConnection.getAllWorkers().enqueue(

            object : Callback<WorkersGetResponse> {
                override fun onResponse(call: Call<WorkersGetResponse>, response: Response<WorkersGetResponse>) {
                    Log.d("RESPONSE", response.body().toString())

                    if(response.body() != null) {
                        workersList = response.body()
                    }
                    pokemonAdapter = PendingWorkersAdapter(workersList?.data)
                    recyclerVariable = view.findViewById(R.id.recycler_container)
                    recyclerVariable.layoutManager = LinearLayoutManager(activityParent, LinearLayoutManager.VERTICAL, false)
                    recyclerVariable.adapter = pokemonAdapter
                }
                override fun onFailure(call: Call<WorkersGetResponse>, t: Throwable) {
                    Log.d("Error", t.toString())
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