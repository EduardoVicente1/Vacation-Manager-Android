package com.example.vacation_manager_android

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    lateinit var txt_usuario: EditText
    lateinit var txt_contrasenia: EditText
    lateinit var btn_login: Button
    lateinit var chk_recordar: CheckBox
    lateinit var txt_olvidado: TextView
    var userprueba=""
    var passprueba=""
    lateinit var sharedP: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*var intentInicio = Intent(this,activitydeinicio::class.java)*/
        sharedP = getSharedPreferences(ObjectSP.K, Context.MODE_PRIVATE)
        val editor = sharedP.edit()

        txt_usuario=findViewById(R.id.txt_user)
        txt_contrasenia=findViewById(R.id.txt_pass)
        txt_olvidado=findViewById(R.id.txt_olvidado)
        btn_login=findViewById(R.id.btn_login)
        chk_recordar=findViewById(R.id.chk_recordar)

        checklogin()
        login()

    }


    fun login(){
        btn_login.setOnClickListener(){
            if(verificar(txt_usuario.text.toString(),txt_contrasenia.text.toString())){
                //loguea
                //startActivity(intentInicio)
            }
        }
    }

    fun verificar(usuario:String, password:String): Boolean{
        var esCorrecto= false
            if(usuario.isNotEmpty()&&password.isNotEmpty()){
                //control userdb no vacio
                if(usuario==userprueba&&password==passprueba) {
                    esCorrecto=true
                }else if(usuario!=userprueba && password!=passprueba){
                    //usuario y contraseña incorrectas
                }else if(password!=passprueba){
                    //contraseña incorrecta
                }else if (usuario!=userprueba){
                    //usuario incorrecto
                }
            }else if(usuario.isNotEmpty()){
                //usuario vacio
            }else if(password.isNotEmpty()){
                //contraseña vacia
            }else{
                //error: Campos vacios
            }
        return esCorrecto
    }

    fun checklogin(){

    }
    
//    fun View.hideKeyboard() {
//        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputManager.hideSoftInputFromWindow(windowToken, 0)
//    }
}