package com.example.vacation_manager_android.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

class PendingWorkersAdapter(var pendingWorkersList : List<WorkersGetResponse.Data?>?, var callback: (workerData : WorkersGetResponse.Data?, action : String) -> Unit ) :  RecyclerView.Adapter<PendingWorkersAdapter.PendingWorkersHolder>(){

    inner class PendingWorkersHolder (var view: View) : RecyclerView.ViewHolder(view){
        var pendingWorkerTeamColor : View = view.findViewById(R.id.pending_worker_team_color)

        var pendingWorkerName : TextView = view.findViewById(R.id.pending_worker_name)
        var pendingWorkerTeam : TextView = view.findViewById(R.id.pending_worker_team)

        var pendingWorkerStartDate : TextView = view.findViewById(R.id.pending_worker_start_Date)
        var pendingWorkerFinishDate : TextView = view.findViewById(R.id.pending_worker_finish_Date)

        var pendingWorkerAccept : ImageView = view.findViewById(R.id.pending_worker_accept_button)
        var pendingWorkerReject : ImageView = view.findViewById(R.id.pending_worker_reject_button)
        var pendingWorkerEdit : ImageView = view.findViewById(R.id.pending_worker_edit_button)

        fun bind(elementList: WorkersGetResponse.Data?){
            pendingWorkerName.text = elementList?.attributes?.workerName.toString()
            pendingWorkerTeam.text = elementList?.attributes?.workTeam.toString()
            pendingWorkerStartDate.text = "Inicia: \n"+elementList?.attributes?.startDate.toString()
            pendingWorkerFinishDate.text = "Finaliza: \n"+elementList?.attributes?.endDate.toString()

            pendingWorkerAccept.setOnClickListener{
                callback(elementList, "accept")
            }

            pendingWorkerReject.setOnClickListener{
                callback(elementList, "reject")
            }

            pendingWorkerEdit.setOnClickListener{
                callback(elementList, "edit")
            }

            when(elementList?.attributes?.workTeam.toString()){
                "Itau Fabrica" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.itau_fabrica))
                "Itau Paseo" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.itau_paseo))
                "Familiar" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.familiar))
                "Atlas" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.atlas))
                "GNB" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.gnb))
                "Sudameris" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.sudameris))
                "RoshkaStudios" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.roshka_studios))
                "Administracion" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.administracion))
                "Diseñadores" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.diseñadores))
                "Operaciones" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.operaciones))
                "QA" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.qa))
                "Soporte" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.soporte))
                "Squadra" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.squadra))
                "Sysadmin" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.sysadmin))
                "Tatakua Team" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.tatakua_team))
                "TH" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.th))
                "Victor Villalba" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.victor_villalba))
                "Walberto Team" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.diseñadores))
                "Manchester United 2" -> pendingWorkerTeamColor.setBackgroundColor(getColor(view.context,R.color.soporte))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingWorkersAdapter.PendingWorkersHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pending_recycler, parent, false)

        return PendingWorkersHolder(view)
    }

    override fun onBindViewHolder(holder: PendingWorkersHolder, position: Int) {
        holder.bind(pendingWorkersList?.get(position))
    }

    override fun getItemCount(): Int {
        Log.d("pendingWorkerList.size",pendingWorkersList?.size.toString())
        return pendingWorkersList?.size!!
    }
//    myadapter.reloadList(queryResult) esto se puede llamar desde el actvity o desde el fragment

//    fun reloadList(filteredList : ArrayList<Contacts>){
//        this.contactsList = filteredList
//        notifyDataSetChanged()
//    }

//    fun removeItem(position: Int) {
//        pendingWorkersList.remove(position)
//    }
}