package com.example.vacation_manager_android


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.vacation_manager_android.Fragments.FragmentLogin

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var fr_login=FragmentLogin.newInstance("","")
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fr_login)
                .commit()

    }



}