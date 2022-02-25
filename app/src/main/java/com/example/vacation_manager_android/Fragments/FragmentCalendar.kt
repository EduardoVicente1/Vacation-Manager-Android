package com.example.vacation_manager_android.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.Adapters.OnVacationWorkersAdapter
import com.example.vacation_manager_android.ArrayWorkerClass
import com.example.vacation_manager_android.HostActivity
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.Retrofit.ApiEndpoints
import com.example.vacation_manager_android.Retrofit.RetrofitClient
import com.example.vacation_manager_android.data_classes.WorkersGetResponse
import com.shuhart.materialcalendarview.CalendarDay
import com.shuhart.materialcalendarview.MaterialCalendarView
import com.shuhart.materialcalendarview.MaterialCalendarView.Companion.SELECTION_MODE_MULTIPLE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentCalendar : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var retroFitConnection : ApiEndpoints
    private var workersList: WorkersGetResponse?= null
    lateinit var calendario: MaterialCalendarView
    lateinit var listaVacaciones: MutableList<ArrayWorkerClass>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_calendar, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaVacaciones = mutableListOf(ArrayWorkerClass(nombre = null, fecha_inicio = null, fecha_final = null))

        var activityParent = activity as HostActivity

        calendario = view.findViewById(R.id.calendar)

        lateinit var onVacationWorkersAdapter: OnVacationWorkersAdapter
        lateinit var recyclerVariable : RecyclerView

        retroFitConnection = RetrofitClient.getInstance()
        retroFitConnection.getAllWorkers().enqueue(

        object : Callback<WorkersGetResponse> {
            override fun onResponse(
                call: Call<WorkersGetResponse>,
                response: Response<WorkersGetResponse>
            ) {
                Log.d("RESPONSE", response.body().toString())

                if (response.body() != null) {
                    workersList = response.body()
                }
                onVacationWorkersAdapter = OnVacationWorkersAdapter(workersList?.data)
                recyclerVariable = view.findViewById(R.id.recycler_on_vacation_container)
                recyclerVariable.layoutManager =
                    LinearLayoutManager(activityParent, LinearLayoutManager.VERTICAL, false)
                    recyclerVariable.adapter = onVacationWorkersAdapter


                    /*Ciclo para almacenar los empleados con vacaciones en una Lista*/
                for(worker in workersList?.data!!){
                    if(worker!!.attributes!!.finish != null){
                        listaVacaciones.add(ArrayWorkerClass(nombre=worker!!.attributes!!.workerName.toString(),
                            fecha_inicio=worker!!.attributes!!.startDate.toString(),
                            fecha_final=worker!!.attributes!!.finish.toString()))
                    }
                }


                            /*CICLO PARA OBTENER FECHAS DE VACACIONES*/
                for(empleadoVacacion in listaVacaciones){
                    if(empleadoVacacion.fecha_final != null){
                        var inicio = empleadoVacacion.fecha_inicio!!.split("-")
                        var fin = empleadoVacacion.fecha_final!!.split("-")
                        calendario.setDateSelected((CalendarDay(year= inicio[2].toInt(), month= inicio[0].toInt() - 1, day= inicio[1].toInt())), true).apply {  }

                        calendario.setDateSelected((CalendarDay(year= fin[2].toInt(), month= fin[0].toInt() - 1, day= fin[1].toInt())), true).apply {  }


                    }
                }

            }

            override fun onFailure(call: Call<WorkersGetResponse>, t: Throwable) {
                Log.d("Error", t.toString())
            }
        })
                /*Cambiar el modo de seleccion del calendario a multiple*/
        calendario.selectionMode = SELECTION_MODE_MULTIPLE

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCalendar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }




}

