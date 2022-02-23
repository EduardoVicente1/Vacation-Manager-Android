package com.example.vacation_manager_android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.*
import androidx.core.view.isVisible
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class LoginActivity : AppCompatActivity() {

    lateinit var txt_usuario: EditText
    lateinit var txt_contrasenia: EditText
    lateinit var btn_login: Button
    lateinit var chk_recordar: CheckBox
    lateinit var txt_olvidado: TextView
    lateinit var btn_token: Button
    lateinit var error: TextView
    var userprueba="asd"
    var passprueba="123"
    private lateinit var sharedP: SharedPreferences

    val errorcsm = Errorcsm()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val masterKey= MasterKey.Builder(applicationContext,MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sharedP = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            EncryptedSharedPreferences.create(applicationContext,"encrypted_${ObjectSP.K}",
                masterKey, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
        } else{
            getSharedPreferences(ObjectSP.K, Context.MODE_PRIVATE)
        }

        txt_usuario=findViewById(R.id.txt_user)
        txt_contrasenia=findViewById(R.id.txt_pass)
        txt_olvidado=findViewById(R.id.txt_olvidado)
        btn_login=findViewById(R.id.btn_login)
        btn_token=findViewById(R.id.btn_enviartoken)
        chk_recordar=findViewById(R.id.chk_recordar)
        error=findViewById(R.id.txt_error)

        check()//inicializa los textview con el user y pass recordados
        login()
        olvidado()

    }

    fun login(){
        btn_login.setOnClickListener(){
            if(verificar(txt_usuario.text.toString(),txt_contrasenia.text.toString())){
                //loguea
                checklogin()//guarda los valores de user y pass si esta seleccionado
                startActivity(Intent(this, HostActivity::class.java))
            }
        }
    }

    fun verificar(usuario:String, password:String): Boolean{
        var esCorrecto= false
        if(usuario.isNotEmpty()&&password.isNotEmpty()){
            //control db no retorna vacio
            if(usuario==userprueba&&password==passprueba) {
                esCorrecto=true
                Toast.makeText(this,"Exito!", Toast.LENGTH_SHORT).show()
            }else if(usuario!=userprueba && password!=passprueba){
                //usuario y contraseña incorrectas
                error.setText("Usuario y contraseña incorrectas")
                errorcsm.texterror(error,this)
            }else if(password!=passprueba){
                //contraseña incorrecta
                error.setText("Contraseña incorrecta")
                errorcsm.texterror(error,this)
            }else if (usuario!=userprueba){
                //usuario incorrecto
                error.setText("Usuario incorrecto")
                errorcsm.texterror(error,this)
            }
        }else if(usuario.isNotEmpty()){
            //usuario vacio
            error.setText("Usuario vacio")
            errorcsm.texterror(error,this)
        }else if(password.isNotEmpty()){
            //contraseña vacia
            error.setText("Contraseña vacia")
            errorcsm.texterror(error,this)
        }else{
            //error: Campos vacios
            error.setText("Campos vacios")
            errorcsm.texterror(error,this)
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

    fun olvidado() {
        txt_olvidado.setOnClickListener {
            btn_token.isVisible = true
        }
        btn_token.setOnClickListener() {
            //se envia el token al correo
            btn_token.isVisible = false
        }
    }

}