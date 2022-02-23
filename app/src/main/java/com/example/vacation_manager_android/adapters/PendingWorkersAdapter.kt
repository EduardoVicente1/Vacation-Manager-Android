package com.example.vacation_manager_android.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

class PendingWorkersAdapter(var pendingWorkersList : List<WorkersGetResponse.Data?>?) :  RecyclerView.Adapter<PendingWorkersAdapter.PendingWorkersHolder>(){

    inner class PendingWorkersHolder (view: View) : RecyclerView.ViewHolder(view){
        var pendingWorkerName : TextView= view.findViewById(R.id.pending_worker_name)
        fun bind(elementList: WorkersGetResponse.Data?){
            pendingWorkerName.text = elementList?.attributes?.workerName.toString()
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