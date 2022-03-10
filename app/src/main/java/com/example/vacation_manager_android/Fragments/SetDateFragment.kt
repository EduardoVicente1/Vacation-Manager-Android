package com.example.vacation_manager_android.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.RetrofitManager
import com.example.vacation_manager_android.data_classes.WorkerPutRequest
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val WORKER_DATA = "workerData"

/**
 * A simple [Fragment] subclass.
 * Use the [SetDateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetDateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var workerData: WorkersGetResponse.Data? = null

    lateinit var activityParent : Activity
    var pendingsFragment = PendingsFragment
    var retrofitManager = RetrofitManager()

    lateinit var startSetDate : EditText
    lateinit var finishSetDate : EditText
    lateinit var saveSetDate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            workerData = it.getParcelable(WORKER_DATA) as WorkersGetResponse.Data?
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityParent = activity as HostActivity

        startSetDate = view.findViewById(R.id.start_set_date)
        finishSetDate = view.findViewById(R.id.finish_set_date)
        saveSetDate = view.findViewById(R.id.save_set_date)

        saveSetDate.setOnClickListener {
            var newWorkerData = WorkerPutRequest(
                WorkerPutRequest.Data(
                    workerData?.attributes?.emailSended,
                    finishSetDate.text.toString(),
                    workerData?.attributes?.onVacation,
                    startSetDate.text.toString(),
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
                Toast.makeText(activityParent, "Fecha guardada exitosamente", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.popBackStack()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.contiene_Fragments, pendingsFragment.newInstance("", ""))
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_date, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SetDateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(arg: WorkersGetResponse.Data?) =
            SetDateFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(WORKER_DATA, arg)
                }
            }
    }
}