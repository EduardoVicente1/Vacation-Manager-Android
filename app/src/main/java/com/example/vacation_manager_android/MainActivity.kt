package com.example.vacation_manager_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(2000)
        setTheme(R.style.Theme_VacationManagerAndroid)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}