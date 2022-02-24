package com.example.vacation_manager_android.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

class CalendarWorkersAdapter(var onVacationWorkersList: List<WorkersGetResponse.Data?>?) : RecyclerView.Adapter<CalendarWorkersAdapter.OnVacationWorkersHolder>(){
    inner class OnVacationWorkersHolder (var view: View) : RecyclerView.ViewHolder(view){
        var workerName : TextView= view.findViewById(R.id.calendar_worker_name)
        var workerTeam : TextView=view.findViewById(R.id.calendar_worker_team)
        lateinit var imgcolor: ImageView
        fun bind(onWorkerElement: WorkersGetResponse.Data?){
            workerName.text = onWorkerElement?.attributes?.workerName.toString()
            var checkonVacation=onWorkerElement?.attributes?.onVacation
            workerTeam.text = "GNB"
//            workerTeam.text = onWorkerElement?.attributes?.workTeam.toString()
            imgcolor=view.findViewById(R.id.img_calendar_worker_color)

            when(workerTeam.text){
                "Itau Fabrica" -> imgcolor.setColorFilter(R.color.itau_fabrica)
                "Itau Paseo" -> imgcolor.setColorFilter(R.color.itau_paseo)
                "Familiar" -> imgcolor.setColorFilter(R.color.familiar)
                "Atlas" -> imgcolor.setColorFilter(R.color.atlas)
                "GNB" -> imgcolor.setColorFilter(R.color.gnb)
                "Sudameris" -> imgcolor.setColorFilter(R.color.sudameris)
                "RoshkaStudios" -> imgcolor.setColorFilter(R.color.roshka_studios)
                "Administracion" -> imgcolor.setColorFilter(R.color.administracion)
                "Diseñadores" -> imgcolor.setColorFilter(R.color.diseñadores)
                "Operaciones" -> imgcolor.setColorFilter(R.color.operaciones)
                "QA" -> imgcolor.setColorFilter(R.color.qa)
                "Soporte" -> imgcolor.setColorFilter(R.color.soporte)
                "Squadra" -> imgcolor.setColorFilter(R.color.squadra)
                "Sysadmin" -> imgcolor.setColorFilter(R.color.sysadmin)
                "Tatakua Team" -> imgcolor.setColorFilter(R.color.tatakua_team)
                "TH" -> imgcolor.setColorFilter(R.color.th)
                "Victor Villalba" -> imgcolor.setColorFilter(R.color.victor_villalba)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarWorkersAdapter.OnVacationWorkersHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_worker_recycler, parent, false)

        return OnVacationWorkersHolder(view)
    }

    override fun onBindViewHolder(holder: OnVacationWorkersHolder, position: Int) {
        holder.bind(onVacationWorkersList?.get(position))
    }

    override fun getItemCount(): Int {
        Log.d("pendingWorkerList.size",onVacationWorkersList?.size.toString())
        return onVacationWorkersList?.size!!
    }
}