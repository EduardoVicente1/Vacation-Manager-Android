package com.example.vacation_manager_android

import android.app.Activity
import android.app.DatePickerDialog
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.vacation_manager_android.database.WorkersDB
import com.example.vacation_manager_android.users.Workers
import com.example.vacation_manager_android.users.WorkersDAO
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentCrudWorkers : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var tx_crudw_nombre: TextView
    lateinit var tx_crudw_cedula: TextView
    lateinit var tx_crudw_correo: TextView
    lateinit var tx_crudw_equipo: TextView
    lateinit var tx_crudw_fecha: TextView
    lateinit var tx_crudw_antiguedad: TextView
    lateinit var btn_new: Button
    lateinit var btn_save: Button
    lateinit var btn_modify: Button
    lateinit var btn_delete: Button
    lateinit var db: WorkersDB
    lateinit var workdao: WorkersDAO
    lateinit var actividad: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tx_crudw_nombre=view.findViewById(R.id.tx_crudW_nombre)
        tx_crudw_cedula=view.findViewById(R.id.tx_crudW_cedula)
        tx_crudw_correo=view.findViewById(R.id.tx_crudW_correo)
        tx_crudw_equipo=view.findViewById(R.id.tx_crudW_equipo)
        tx_crudw_fecha=view.findViewById(R.id.tx_crudW_fecha)
        tx_crudw_antiguedad=view.findViewById(R.id.tx_crudW_antiguedad)
        btn_new=view.findViewById(R.id.btn_crudW_new)
        btn_save=view.findViewById(R.id.btn_crudW_save)
        btn_modify=view.findViewById(R.id.btn_crudW_modify)
        btn_delete=view.findViewById(R.id.btn_crudW_delete)

        (activity as Activity).also { actividad = it as MainActivity }
        db= WorkersDB.getInstance(view.context)!!
        workdao= db.workersDAO()

        tx_crudw_fecha.setOnClickListener{showDatePickerDialog()}
//            var cal= Calendar.getInstance()
//            var year = cal.get(Calendar.YEAR)
//            var month = cal.get(Calendar.MONTH)
//            var dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
//
//            val myFormat = "dd/MM/yyyy" // formato dia,mes,año
//            val sdf = SimpleDateFormat(myFormat, Locale.US)
//            tx_crudw_fecha.text = sdf.format(cal.time)


//            val dpd= DatePickerDialog(actividad, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
//
//                tx_crudw_fecha.setText("" + dayOfMonth + " / " + month + " / " + year)
//            },year, month, dayOfMonth)
//            dpd.show()

        btn_save.setOnClickListener{
            if(tx_crudw_nombre.text!=null && tx_crudw_cedula.text!=null && tx_crudw_correo.text!=null &&
                tx_crudw_equipo.text!=null && tx_crudw_fecha.text!=null && tx_crudw_antiguedad.text!=null){
                workdao.insertWorkers(
                    Workers(
                        UUID.randomUUID().toString(),
                        tx_crudw_nombre.text.toString(),
                        tx_crudw_cedula.text.toString().toInt(),
                        tx_crudw_correo.text.toString(),
                        tx_crudw_equipo.text.toString(),
                        tx_crudw_fecha.text.toString().toLong(),
                        tx_crudw_antiguedad.text.toString().toInt()
                    )
                )
                Toast.makeText(context, "Registro Creado Correctamente", Toast.LENGTH_SHORT).show()
                actividad.finish()
            }else{
                Toast.makeText(context, "Por favor complete el formulario " +
                        "correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerClass{day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(parentFragmentManager,"datePicker")
    }

    fun onDateSelected(day:Int, month:Int, year:Int){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crud_workers, container, false)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCrudWorkers().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}