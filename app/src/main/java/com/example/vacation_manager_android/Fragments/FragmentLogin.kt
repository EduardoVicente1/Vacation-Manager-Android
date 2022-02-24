package com.example.vacation_manager_android.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.vacation_manager_android.Adapters.OnVacationWorkersAdapter
import com.example.vacation_manager_android.Errorcsm
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.ObjectSP
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.ApiEndpoints
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import com.example.vacation_manager_android.data_classes.UserGetResponse
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
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLogin : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    /*********************************************/
    lateinit var txt_usuario: EditText
    lateinit var txt_contrasenia: EditText
    lateinit var btn_login: Button
    lateinit var chk_recordar: CheckBox
    lateinit var txt_olvidado: TextView
    lateinit var error: TextView
    var userprueba="asd"
    var passprueba="123"
    private lateinit var sharedP: SharedPreferences

    lateinit var txtRegistro: TextView

    val errorcsm = Errorcsm()

    lateinit var retroFitConnection : ApiEndpoints
    private var userbd: UserGetResponse?= null

    /**********************************************/

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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retroFitConnection=RetrofitClient.getInstance()
        retroFitConnection.getUser().enqueue(

            object : Callback<UserGetResponse> {
                override fun onResponse(call: Call<UserGetResponse>, response: Response<UserGetResponse>) {
                    Log.d("RESPONSE", response.body().toString())

                    if(response.body() != null) {
                        userbd = response.body()
                    }
                }
                override fun onFailure(call: Call<UserGetResponse>, t: Throwable) {
                    Log.d("Error", t.toString())
                }
            }
        )
        /************************************************/
        val masterKey= MasterKey.Builder(requireContext().applicationContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sharedP = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            EncryptedSharedPreferences.create(requireContext().applicationContext,"encrypted_${ObjectSP.K}",
                masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
        } else{
            requireContext().getSharedPreferences(ObjectSP.K, Context.MODE_PRIVATE)
        }
        /************************************************/
        txt_usuario=view.findViewById(R.id.txt_user)
        txt_contrasenia=view.findViewById(R.id.txt_pass)
        txt_olvidado=view.findViewById(R.id.txt_olvidado)
        btn_login=view.findViewById(R.id.btn_login)
        chk_recordar=view.findViewById(R.id.chk_recordar)
        error=activity?.findViewById(R.id.txt_error)!!
        txtRegistro=view.findViewById(R.id.txt_registrarse)



        /*******Llamado a funciones*******/
        check()//inicializa los textview con el user y pass recordados
        login()
        olvidado()
        registrar()
        /*********************************/

        /*********************************/
    }
    /******************Funciones*******************/
    fun login(){
        btn_login.setOnClickListener(){
            if(verificar(txt_usuario.text.toString(),txt_contrasenia.text.toString())){
                //loguea
                checklogin()//guarda los valores de user y pass si esta seleccionado
                startActivity(Intent(requireContext(), HostActivity::class.java))
            }
        }
    }

    fun verificar(usuario:String, password:String): Boolean{
        var esCorrecto= false
        if(usuario.isNotEmpty()&&password.isNotEmpty()){
            //control db no retorna vacio
            if(checkdatabase(usuario)) {
                if (usuario == userprueba && password == passprueba) {
                    esCorrecto = true

                } else if (usuario != userprueba && password != passprueba) {
                    //usuario y contraseña incorrectas
                    error.setText("Usuario y contraseña incorrectas")
                    errorcsm.texterror(error, requireContext())
                } else if (password != passprueba) {
                    //contraseña incorrecta
                    error.setText("Contraseña incorrecta")
                    errorcsm.texterror(error, requireContext())
                }
            }else{
                error.text="Ese usuario no existe"
                errorcsm.texterror(error,requireContext())
            }
        }else if(usuario.isNotEmpty()){
            //contraseña vacia
            error.setText("Contraseña vacia")
            errorcsm.texterror(error,requireContext())
        }else if(password.isNotEmpty()){
            //usuario vacio
            error.setText("Usuario vacio")
            errorcsm.texterror(error,requireContext())
        }else{
            //error: Campos vacios
            error.setText("Campos vacios")
            errorcsm.texterror(error,requireContext())
        }
        return esCorrecto
    }

    fun checklogin(){
        val editor = sharedP.edit()
        if(chk_recordar.isChecked){
            editor.putString(ObjectSP.USER,txt_usuario.text.toString())
            editor.putString(ObjectSP.PASS,txt_contrasenia.text.toString())
            editor.putString(ObjectSP.CHECK,"true")
        }else{
            editor.putString(ObjectSP.CHECK,"false")
        }
        editor.commit()
    }

    fun check(){
        var speditor = sharedP.getString(ObjectSP.CHECK,"false")
        if(speditor=="true"){
            txt_usuario.setText(sharedP.getString(ObjectSP.USER,""))
            txt_contrasenia.setText(sharedP.getString(ObjectSP.PASS,""))
            chk_recordar.isChecked=true
        }
    }

    fun olvidado() {//fragment para recuperar la contraseña
        txt_olvidado.setOnClickListener {
           var fr_olvidado = FragmentRecordarPass.newInstance("","")
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,fr_olvidado)
                .addToBackStack(null)
                .commit()
        }
    }

    fun registrar(){//fragment para registrar usuario
        txtRegistro.setOnClickListener(){
            var fr_registrar = FragmentRegister.newInstance("","")
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,fr_registrar)
                .addToBackStack(null)
                .commit()
        }
    }

    fun checkdatabase(usuario:String): Boolean{
        var existeUser=false
        if(userbd!=null) {
            for (i in userbd?.data?.indices!!){
                if(userbd!!.data?.get(i)?.attributes?.username.toString()==usuario){
                    existeUser=true
                    userprueba=usuario
                    passprueba=userbd!!.data?.get(i)?.attributes?.password.toString()
                }
            }
        }
        return existeUser
    }

    /********************************************************/
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLogin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLogin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}