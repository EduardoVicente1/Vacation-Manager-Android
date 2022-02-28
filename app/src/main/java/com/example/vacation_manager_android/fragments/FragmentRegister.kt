package com.example.vacation_manager_android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vacation_manager_android.BaseFragment
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.retrofit.ApiEndpoints
import com.example.vacation_manager_android.retrofit.RetrofitClient
import com.example.vacation_manager_android.data_classes.UserGetResponse
import com.example.vacation_manager_android.data_classes.UserInfoRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRegister.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRegister : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    /***************************/
    lateinit var btn_registro: Button
    lateinit var txt_user: EditText
    lateinit var txt_email: EditText
    lateinit var txt_pass1: EditText
    lateinit var txt_pass2: EditText
    lateinit var txterror: TextView

    lateinit var retroFitConnection : ApiEndpoints
    private var userInfo: UserInfoRegister?= null
    private var userbd: UserGetResponse?= null
    var passEncript=""

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retroFitConnection= RetrofitClient.getInstance()
        retroFitConnection.getUser().enqueue(

            object : Callback<UserGetResponse> {
                override fun onResponse(call: Call<UserGetResponse>, response: Response<UserGetResponse>) {
                   // Log.d("RESPONSE", response.body().toString())

                    if(response.body() != null) {
                        userbd = response.body()
                    }
                }
                override fun onFailure(call: Call<UserGetResponse>, t: Throwable) {
                  //  Log.d("Error", t.toString())
                }
            }
        )

        txt_user=view.findViewById(R.id.txt_newuser)
        txt_email=view.findViewById(R.id.txt_email)
        txt_pass1=view.findViewById(R.id.txt_npass1)
        txt_pass2=view.findViewById(R.id.txt_npass2)
        btn_registro=view.findViewById(R.id.btn_registrarse)
        txterror = activity?.findViewById(R.id.txt_error)!!

        registrarse()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRegister.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRegister().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*************************funciones***************************/

    fun registrarse(){
        btn_registro.setOnClickListener(){
            //verificar que los campos no esten vacios
            if(txt_user.text.toString().isNotEmpty()&& txt_pass1.text.toString().isNotEmpty()&&txt_email.text.toString().isNotEmpty()) {
                /***Se verifica que el user no este registrado***/
                if(noExisteUser(txt_user.text.toString())){
                    if(noExisteMail(txt_email.text.toString())) {
                        /*****Si no esta registrado se verifica que las pass sean iguales*****/
                        if (txt_pass1.text.toString() == txt_pass2.text.toString()) {
                            //se guarda en la base de datos

                                passEncript=encriptar(txt_pass1.text.toString(),clave)
                            userInfo = UserInfoRegister(
                                UserInfoRegister.Data(
                                    password = passEncript,
                                    tokenApp = "AAAAAAAAAAA",
                                    userEmail = txt_email.text.toString(),
                                    username = txt_user.text.toString()
                                )
                            )
                            addUser(userInfo!!) {}/***se añade a la base de datos***/
                            txterror.text = "Guardado con exito"
                            texterror(txterror, requireContext())
                            var frlogin=FragmentLogin.newInstance("","")
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container,frlogin)
                                .commit()
                            /***********se vuelve al login***********/
                            /***********************************************************************/
                            /**A partir de aqui el manejo de errores**/
                        } else {
                            txterror.text = "No coinciden las contraseñas"
                            texterror(txterror, requireContext())
                        }
                    }
                }else{
                    txterror.text="Usuario ya registrado"
                    texterror(txterror,requireContext())
                    txt_user.setText("")
                 }
            }else if(txt_user.text.toString().isNotEmpty()&&txt_email.text.toString().isNotEmpty()){
                txterror.text="Contraseña no puede estar vacia"
                texterror(txterror,requireContext())
            }else if(txt_email.text.toString().isNotEmpty()&&txt_pass1.text.toString().isNotEmpty()){
                txterror.text="Usuario no puede estar vacio"
                texterror(txterror,requireContext())
            }else if(txt_user.text.toString().isNotEmpty() && txt_pass1.text.toString().isNotEmpty()){
                txterror.text="E-mail no puede ser vacio"
                texterror(txterror,requireContext())
            }else{
                txterror.text="Todos los campos deben ser rellenados!"
                texterror(txterror,requireContext())
            }
        }
    }
    /**verificamos que no exista user igual**/
    fun noExisteUser(user: String): Boolean{
        var noExiste=true

        if(userbd!=null) {
            for (i in userbd?.data?.indices!!){
                if(userbd!!.data?.get(i)?.attributes?.username.toString()==user){
                    noExiste=false
                }
            }
        }
        return noExiste
    }
    /**verificamos que no se repita el email**/
    fun noExisteMail(mail:String):Boolean{
        var noExiste=true
        for (i in userbd?.data?.indices!!) {
            if (userbd!!.data?.get(i)?.attributes?.userEmail.toString() == mail) {
                txterror.text = "E-mail ya registrado"
                txt_email.setText("")
                texterror(txterror, requireContext())
                noExiste = false
            }
        }
        return noExiste
    }
    /*******agregamos el nuevo user*******/
    fun addUser(userData: UserInfoRegister, onResult: (UserInfoRegister?) -> Unit){
        retroFitConnection.addUser(userData).enqueue(
            object : Callback<UserInfoRegister> {
                override fun onFailure(call: Call<UserInfoRegister>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UserInfoRegister>, response: Response<UserInfoRegister>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }


}