package com.example.vacation_manager_android.data_classes


import com.google.gson.annotations.SerializedName

data class WorkersGetResponse(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("meta")
    val meta: Meta?
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("id")
        val id: Int?
    ) {
        data class Attributes(
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("publishedAt")
            val publishedAt: String?,
            @SerializedName("start_date")
            val startDate: String?,
            @SerializedName("updatedAt")
            val updatedAt: String?,
            @SerializedName("work_mail")
            val workMail: String?,
            @SerializedName("work_team")
            val workTeam: String?,
            @SerializedName("worker_name")
            val workerName: String?,
            @SerializedName("email_sended")
            val emailSender: Boolean?,
            @SerializedName("on_vacation")
            val onVacation: Boolean?
        )
    }

    data class Meta(
        @SerializedName("pagination")
        val pagination: Pagination?
    ) {
        data class Pagination(
            @SerializedName("page")
            val page: Int?,
            @SerializedName("pageCount")
            val pageCount: Int?,
            @SerializedName("pageSize")
            val pageSize: Int?,
            @SerializedName("total")
            val total: Int?
        )
    }
}