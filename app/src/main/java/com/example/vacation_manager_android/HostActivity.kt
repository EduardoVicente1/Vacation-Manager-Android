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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.vacation_manager_android.Fragments.FragmentHome
import com.example.vacation_manager_android.Fragments.FragmentVacationReque
import com.google.android.material.navigation.NavigationView
import com.paulocabelloacha.sendnotif.SendNotifFragment

class HostActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var fragmentHome: Fragment
    lateinit var fragmentvacation: Fragment
    lateinit var fragmentSendNotif: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        drawerLayout=findViewById(R.id.drawerLayout)
        navView=findViewById(R.id.nav_main)
        fragmentHome= FragmentHome.newInstance("","")
        fragmentvacation= FragmentVacationReque.newInstance("","")
        fragmentSendNotif= SendNotifFragment.newInstance("","")

        toggle= ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener(this)

        cargarFragment(R.id.m_item1)
    }
    //Sobreescribir la Función para seleccionar item del menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.m_item1 -> supportFragmentManager.popBackStack()
            R.id.m_item2 -> cargarFragment(R.id.m_item2)
            R.id.m_item3 -> cargarFragment(R.id.m_item3)
            R.id.m_item4 -> Toast.makeText(applicationContext,
                "Clicked Item 4", Toast.LENGTH_SHORT).show()
            R.id.m_item5 -> Toast.makeText(applicationContext,
                "Clicked Item 5", Toast.LENGTH_SHORT).show()
            R.id.m_item6 -> Toast.makeText(applicationContext,
                "Clicked Item 6", Toast.LENGTH_SHORT).show()
            R.id.m_item7 -> Toast.makeText(applicationContext,
                "Clicked Item 7", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    //función para seleccionar item del menu
    fun cargarFragment(pantalla: Int){
        when(pantalla){
            R.id.m_item1 ->{
                supportFragmentManager.beginTransaction()
                    .add(R.id.contiene_Fragments,fragmentHome)
                    .setPrimaryNavigationFragment(fragmentHome)
                    .commit()
                }
            R.id.m_item2 ->{
                supportFragmentManager.popBackStack("BorrateCRJ", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contiene_Fragments, fragmentvacation)
                    .addToBackStack(null)
                    .commit()}
            R.id.m_item3 ->{
                supportFragmentManager.popBackStack()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.contiene_Fragments, fragmentSendNotif)
                    .addToBackStack(null)
                    .commit()}
            R.id.m_item4 -> Toast.makeText(applicationContext,
                "Clicked Item 4", Toast.LENGTH_SHORT).show()
            R.id.m_item5 -> Toast.makeText(applicationContext,
                "Clicked Item 5", Toast.LENGTH_SHORT).show()
            R.id.m_item6 -> Toast.makeText(applicationContext,
                "Clicked Item 6", Toast.LENGTH_SHORT).show()
            R.id.m_item7 -> Toast.makeText(applicationContext,
                "Clicked Item 7", Toast.LENGTH_SHORT).show()
        }
    }
    fun removeFragmentHome(pantalla: Int){

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}