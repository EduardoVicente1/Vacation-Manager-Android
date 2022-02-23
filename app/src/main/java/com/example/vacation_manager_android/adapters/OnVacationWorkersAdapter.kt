package com.example.vacation_manager_android.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vacation_manager_android.R
import com.example.vacation_manager_android.data_classes.WorkersGetResponse

class OnVacationWorkersAdapter(var onVacationWorkersList: List<WorkersGetResponse.Data?>?) : RecyclerView.Adapter<OnVacationWorkersAdapter.OnVacationWorkersHolder>(){
    inner class OnVacationWorkersHolder (var view: View) : RecyclerView.ViewHolder(view){
        var onVacationWorkerName : TextView= view.findViewById(R.id.on_vacation_worker_name)
        fun bind(onWorkerElement: WorkersGetResponse.Data?){
            onVacationWorkerName.text = onWorkerElement?.attributes?.workerName.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnVacationWorkersAdapter.OnVacationWorkersHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_on_vacation_worker_recycler, parent, false)

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