package com.example.vacation_manager_android

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout=findViewById(R.id.drawerLayout)
        navView=findViewById(R.id.nav_main)

        toggle= ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener(this)

    }

    //Sobreescribir la Función para seleccionar item del menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmenthome= FragmentHome.newInstance("","")
        val fragmentvacation= FragmentVacationReque.newInstance("","")
        val fragmentCrudWorkers= FragmentCrudWorkers.newInstance("","")
        when(item.itemId){
            R.id.m_item1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.contiene_Fragments,fragmenthome)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
            R.id.m_item2 -> supportFragmentManager.beginTransaction()
                .replace(R.id.contiene_Fragments, fragmentvacation)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
            R.id.m_item3 -> supportFragmentManager.beginTransaction()
                .replace(R.id.contiene_Fragments, fragmenthome)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
            R.id.m_item4 -> Toast.makeText(applicationContext,
                "Clicked Item 4", Toast.LENGTH_SHORT).show()
            R.id.m_item5 -> Toast.makeText(applicationContext,
                "Clicked Item 5", Toast.LENGTH_SHORT).show()
            R.id.m_item6 -> supportFragmentManager.beginTransaction()
                .replace(R.id.contiene_Fragments, fragmentCrudWorkers)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    //Sobreescribir la función para ocultar teclado al clickear en cualquier parte de la pantalla
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    Log.d("focus", "touchevent")
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}