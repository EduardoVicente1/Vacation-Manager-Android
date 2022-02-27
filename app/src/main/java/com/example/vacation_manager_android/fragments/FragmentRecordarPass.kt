package com.example.vacation_manager_android.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vacation_manager_android.Errorcsm
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.retrofit.ApiEndpoints
import com.example.vacation_manager_android.retrofit.RetrofitClient
import com.example.vacation_manager_android.data_classes.UserGetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRecordarPass.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRecordarPass : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    /**********************************/
    lateinit var txt_campomail: EditText
    lateinit var btn_token: Button
    lateinit var errortxt: TextView
    var eMail= ""

    lateinit var retroFitConnection : ApiEndpoints
    private var userbd: UserGetResponse?= null
    val errorcsm = Errorcsm()
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
        return inflater.inflate(R.layout.fragment_recordar_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retroFitConnection= RetrofitClient.getInstance()
        retroFitConnection.getUser().enqueue(

            object : Callback<UserGetResponse> {
                override fun onResponse(call: Call<UserGetResponse>, response: Response<UserGetResponse>) {
                    if(response.body() != null) {
                        userbd = response.body()
                    }
                }
                override fun onFailure(call: Call<UserGetResponse>, t: Throwable) {
                    Log.d("Error", t.toString())
                }
            }
        )
        txt_campomail=view.findViewById(R.id.txt_correo)
        btn_token=view.findViewById(R.id.btn_validarcorreo)
        errortxt=activity?.findViewById(R.id.txt_error)!!

        verificar()

    }

    fun verificar(){
        btn_token.setOnClickListener(){
            if(txt_campomail.text.toString().isNotEmpty()){
                //envio del token al correo dado solo si se encuentra registrado el correo
                if(checkemail(txt_campomail.text.toString())){
                    //aqui envio el correo
                }else{
                    errortxt.text="E-mail no registrado"
                    errorcsm.texterror(errortxt,requireContext())
                }
            }else{
                errortxt.text="Ingrese su e-mail!"
                errorcsm.texterror(errortxt,requireContext())
            }
        }
    }

    fun checkemail(email: String): Boolean{
        var existeMail=false
        if(userbd!=null){
            for(i in userbd?.data?.indices!!){
                if(email== userbd?.data?.get(i)?.attributes?.userEmail.toString()){
                    existeMail=true
                    eMail=email
                }
            }
        }
        return existeMail
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRecordarPass.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRecordarPass().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}