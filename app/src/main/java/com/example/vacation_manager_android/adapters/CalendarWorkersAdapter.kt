package com.example.vacation_manager_android.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
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
            workerTeam.text = onWorkerElement?.attributes?.workTeam.toString()
            imgcolor=view.findViewById(R.id.img_calendar_worker_color)

            when(workerTeam.text.toString()){
                "Itau Fabrica" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.itau_fabrica))
                "Itau Paseo" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.itau_paseo))
                "Familiar" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.familiar))
                "Atlas" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.atlas))
                "GNB" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.gnb))
                "Sudameris" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.sudameris))
                "RoshkaStudios" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.roshka_studios))
                "Administracion" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.administracion))
                "Dise??adores" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.dise??adores))
                "Operaciones" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.operaciones))
                "QA" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.qa))
                "Soporte" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.soporte))
                "Squadra" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.squadra))
                "Sysadmin" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.sysadmin))
                "Tatakua Team" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.tatakua_team))
                "TH" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.th))
                "Victor Villalba" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.victor_villalba))
                "Walberto Team" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.dise??adores))
                "Manchester United 2" -> imgcolor.setBackgroundColor(getColor(view.context,R.color.soporte))
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