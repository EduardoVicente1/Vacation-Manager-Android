package com.example.vacation_manager_android.data_classes

import com.google.gson.annotations.SerializedName

data class WorkersPutRequest(

@SerializedName("data")
var `data`: Data?
) {
    data class Data(
        @SerializedName("worker_name")
        var workerName: String?,

        @SerializedName("work_team")
        var workTeam: String?,

        @SerializedName("start_date")
        var startDate: String?,

        @SerializedName("work_mail")
        var workMail: String?,

        @SerializedName("email_sended")
        var emailSended: Boolean?,

        @SerializedName("on_vacation")
        var onVacation: Boolean?,

        @SerializedName("end_date") //
        var endDate: String?
    )
}