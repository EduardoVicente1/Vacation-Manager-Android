package com.example.vacation_manager_android.data_classes


import com.google.gson.annotations.SerializedName

data class WorkerPutRequest(
    @SerializedName("data")
    var `data`: Data?
) {
    data class Data(
        @SerializedName("email_sended")
        var emailSended: Boolean?,
        @SerializedName("end_date")
        var endDate: String?,
        @SerializedName("on_vacation")
        var onVacation: Boolean?,
        @SerializedName("start_date")
        var startDate: String?,
        @SerializedName("work_mail")
        var workMail: String?,
        @SerializedName("work_team")
        var workTeam: String?,
        @SerializedName("worker_name")
        var workerName: String?
    )
}