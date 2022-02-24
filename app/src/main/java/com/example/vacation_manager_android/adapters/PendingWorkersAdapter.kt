package com.example.vacation_manager_android.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

class PendingWorkersAdapter(var pendingWorkersList : List<WorkersGetResponse.Data?>?, var callback: (workerData : WorkersGetResponse.Data?, action : String) -> Unit ) :  RecyclerView.Adapter<PendingWorkersAdapter.PendingWorkersHolder>(){

    inner class PendingWorkersHolder (view: View) : RecyclerView.ViewHolder(view){
        var pendingWorkerName : TextView = view.findViewById(R.id.pending_worker_name)
        var pendingWorkerTeam : TextView = view.findViewById(R.id.pending_worker_team)

        var pendingWorkerAccept : ImageView = view.findViewById(R.id.pending_worker_accept_button)
        var pendingWorkerReject : ImageView = view.findViewById(R.id.pending_worker_reject_button)
        var pendingWorkerEdit : ImageView = view.findViewById(R.id.pending_worker_edit_button)

        fun bind(elementList: WorkersGetResponse.Data?){
            pendingWorkerName.text = elementList?.attributes?.workerName.toString()
            pendingWorkerTeam.text = elementList?.attributes?.workTeam.toString()

            pendingWorkerAccept.setOnClickListener{
                callback(elementList, "accept")
            }

            pendingWorkerReject.setOnClickListener{
                callback(elementList, "reject")
            }

            pendingWorkerEdit.setOnClickListener{
                callback(elementList, "edit")
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
}