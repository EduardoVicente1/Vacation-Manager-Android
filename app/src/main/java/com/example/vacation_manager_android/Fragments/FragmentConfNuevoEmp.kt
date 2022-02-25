package com.example.vacation_manager_android.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.ApiEndpoints
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentConfNuevoEmp.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentConfNuevoEmp : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var txt_worker: TextInputEditText
    lateinit var txt_workerEquipo: TextInputEditText
    lateinit var txt_workerMail: TextInputEditText
    lateinit var txt_dia: EditText
    lateinit var txt_mes: EditText
    lateinit var txt_anio: EditText

    var fecha=""
    lateinit var btnSave: Button

    lateinit var retroFitConnection: ApiEndpoints
    private var allworker: WorkersGetResponse?=null
    private var filteredWorkersList : List<WorkersGetResponse.Data?>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conf_nuevo_emp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_worker=view.findViewById(R.id.tx_new_nombre)
        txt_workerEquipo=view.findViewById(R.id.tx_new_equipo)
        txt_workerMail=view.findViewById(R.id.tx_new_correo)
        btnSave=view.findViewById(R.id.btn_new_save)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentConfNuevoEmp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FragmentConfNuevoEmp().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}