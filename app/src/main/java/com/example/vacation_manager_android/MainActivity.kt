package com.example.vacation_manager_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    lateinit var btn_boton : Button
    lateinit var error : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val errorcsm = Errorcsm()

        error = findViewById(R.id.txt_error)
        btn_boton = findViewById(R.id.btn_tocame)

        btn_boton.setOnClickListener {
            errorcsm.texterror(error,this)



        }
    }
    
}
