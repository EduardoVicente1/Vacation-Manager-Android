package com.example.vacation_manager_android

import android.content.Context
import android.nfc.Tag
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible


class Errorcsm {

    fun texterror(textView: TextView,context: Context){
        var animationSlideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        textView.isVisible=true
        textView.startAnimation(animationSlideDown)
        textView.isVisible=false


    }

}