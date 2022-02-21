package com.example.vacation_manager_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class SplashScreen : AppCompatActivity() {
    private var Splash_Screen_time : Long = 3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, Splash_Screen_time)

    }
}