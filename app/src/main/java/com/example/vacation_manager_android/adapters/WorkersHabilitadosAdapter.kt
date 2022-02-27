package com.example.vacation_manager_android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

class WorkersHabilitadosAdapter(var habilitadosAdapter: List<WorkersGetResponse.Data?>?,var callback: (id:String)->Unit): RecyclerView.Adapter<WorkersHabilitadosAdapter.WorkersHabilitadosHolder>() {

    inner class WorkersHabilitadosHolder (var view: View) : RecyclerView.ViewHolder(view){
        var habilitadosNombres : TextView = view.findViewById(R.id.habilitados_card_view)
        var habilitadosTeam : TextView = view.findViewById(R.id.team_habilitados_card_view)
        var card: View=view.findViewById(R.id.card_habilitados)
        lateinit var imgHabilitados : ImageView
        fun bind(habilitadosElement : WorkersGetResponse.Data?){
            val id = habilitadosElement?.id

            card.setOnClickListener {
                callback(id.toString())


            }
            habilitadosNombres.text = habilitadosElement?.attributes?.workerName.toString()
            habilitadosTeam.text = habilitadosElement?.attributes?.workTeam.toString()
            imgHabilitados = view.findViewById(R.id.img_card_habilitados)

            when(habilitadosTeam.text.toString()){
                "Itau Fabrica" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.itau_fabrica))
                "Itau Paseo" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.itau_paseo))
                "Familiar" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.familiar))
                "Atlas" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.atlas))
                "GNB" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.gnb))
                "Sudameris" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.sudameris))
                "RoshkaStudios" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.roshka_studios))
                "Administracion" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.administracion))
                "Diseñadores" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.diseñadores))
                "Operaciones" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.operaciones))
                "QA" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.qa))
                "Soporte" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.soporte))
                "Squadra" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.squadra))
                "Sysadmin" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.sysadmin))
                "Tatakua Team" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.tatakua_team))
                "TH" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.th))
                "Victor Villalba" -> imgHabilitados.setBackgroundColor(ContextCompat.getColor(view.context,
                    R.color.victor_villalba))
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkersHabilitadosAdapter.WorkersHabilitadosHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_habilitados,parent,false)

        return WorkersHabilitadosHolder(view)
    }

    override fun onBindViewHolder(holder: WorkersHabilitadosHolder, position: Int) {
        holder.bind(habilitadosAdapter?.get(position))
    }

    override fun getItemCount(): Int {
        return habilitadosAdapter?.size!!
    }
}