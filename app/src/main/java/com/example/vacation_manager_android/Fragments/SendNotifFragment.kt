package com.paulocabelloacha.sendnotif

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.DbService
import com.example.vacation_manager_android.data_classes.RetrofitInstance
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import com.example.vacation_manager_android.data_classes.WorkersPutRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SendNotifFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var workersGetResponse: WorkersGetResponse? = null
    private var workersPutRequest: WorkersPutRequest? = null
    private var workersPutResponse: Any? = null

    private lateinit var etBody: EditText
    private lateinit var btnEmail: Button
    private lateinit var emailAddresses: Array<String>
    private lateinit var subject: String
    private lateinit var body: String
    private lateinit var startDate: EditText
    private lateinit var endDate: EditText
    private lateinit var workerEmail: TextView
    private var workMail: String? = ""
    private var workerName:String? = ""

    private val workerId: Int = 33

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_notif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariables(view)
        btnEmail.setOnClickListener {

            body = etBody.text.toString()
            updateWorker()

            val selectorIntent = Intent(Intent.ACTION_SENDTO)
            selectorIntent.setData(Uri.parse("mailto:"))

            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_EMAIL, emailAddresses)
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            sendIntent.putExtra(Intent.EXTRA_TEXT, body)
            sendIntent.setSelector(selectorIntent)
            activity?.startActivity(Intent.createChooser(sendIntent, "Send email..."));
            //updateWorker()
        }
    }

    private fun getWorkerById(workerId: Int) {
        val retroInstance = RetrofitInstance.getRetroInstance().create(DbService::class.java)
        val call = retroInstance.getUserById(workerId)
        call.enqueue(object : Callback<WorkersGetResponse> {
            override fun onResponse(call: Call<WorkersGetResponse>, response: Response<WorkersGetResponse>) {
                if (response.isSuccessful) {
                    workersGetResponse = response.body()
                }
            }
            override fun onFailure(call: Call<WorkersGetResponse>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
                workersGetResponse = null
            }
        })
    }

    private fun updateWorker() {
        val emailSent = true
        val onVacation = false
        val workTeam = workersGetResponse!!.data?.get(0)?.attributes!!.workTeam
        val startDate = startDate.text.toString()
        val endDate = endDate.text.toString()

        val retroInstance = RetrofitInstance.getRetroInstance().create(DbService::class.java)

        val data = WorkersPutRequest.Data(workerName, workTeam,
            startDate, workMail, emailSent, onVacation, endDate)

        val workersPutRequest = WorkersPutRequest(data)

        val call = retroInstance.putWorker(id, workersPutRequest)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    val a = 1
                    Log.i("PUT onResponse", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {

            }
        } )

    }

    private fun initVariables(view: View) {

        getWorkerById(workerId)

        workMail = workersGetResponse!!.data?.get(0)?.attributes!!.workMail
        workerName = workersGetResponse!!.data?.get(0)?.attributes!!.workerName
        etBody = view.findViewById(R.id.et_body)
        btnEmail = view.findViewById(R.id.btn_email)
        emailAddresses = arrayOf("mdocarmo@roshka.com")
        subject = "ROSHKA - Notificación de vacaciones cusadas"
        body = etBody.text.toString()
        startDate = view.findViewById<EditText>(R.id.et_start_date)
        endDate = view.findViewById<EditText>(R.id.et_end_date)
        workerEmail = view.findViewById<TextView>(R.id.tv_worker_email)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SendNotifFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}