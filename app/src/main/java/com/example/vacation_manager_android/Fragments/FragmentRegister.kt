package com.example.vacation_manager_android.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.vacation_manager_android.Errorcsm
import com.example.vacation_manager_android.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRegister.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRegister : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    /***************************/
    lateinit var btn_registro: Button
    lateinit var txt_user: EditText
    lateinit var txt_email: EditText
    lateinit var txt_pass1: EditText
    lateinit var txt_pass2: EditText
    val errorc=Errorcsm()
    lateinit var txterror: TextView

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


    fun registrarse(){
        btn_registro.setOnClickListener(){
            //verificar que los campos no esten vacios
            if(txt_user.text.toString().isNotEmpty()&& txt_pass1.text.toString().isNotEmpty()&&txt_email.text.toString().isNotEmpty()) {
                /***Se verifica que el user no este registrado***/
                //if(consultadb(user)==null){
                /*****Si no esta registrado se verifica que las pass sean iguales*****/
                    if(txt_pass1.text.toString()==txt_pass2.text.toString()){
                        //se guarda en la base de datos
                        txterror.text="Guardado con exito"
                        errorc.texterror(txterror,requireContext())
                    parentFragmentManager.popBackStack()
                    }else{
                        txterror.text="No coinciden las contraseñas"
                        errorc.texterror(txterror,requireContext())
                    }
                //}else{
                //      txterror.text="Usuario ya registrado,elija otro username"
                //      txt_user.text=""
                // }
            }else if(txt_user.text.toString().isNotEmpty()&&txt_email.text.toString().isNotEmpty()){
                txterror.text="Contraseña no puede estar vacia"
                errorc.texterror(txterror,requireContext())
            }else if(txt_email.text.toString().isNotEmpty()&&txt_pass1.text.toString().isNotEmpty()){
                txterror.text="Usuario no puede estar vacio"
                errorc.texterror(txterror,requireContext())
            }else if(txt_user.text.toString().isNotEmpty() && txt_pass1.text.toString().isNotEmpty()){
                txterror.text="E-mail no puede ser vacio"
                errorc.texterror(txterror,requireContext())
            }else{
                txterror.text="Todos los campos deben ser rellenados!"
                errorc.texterror(txterror,requireContext())
            }



        }


    }
}