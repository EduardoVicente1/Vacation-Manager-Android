package com.example.vacation_manager_android.data_classes


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class WorkersGetResponse(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("meta")
    var meta: Meta?
) {
    @Parcelize
    data class Data(
        @SerializedName("attributes")
        var attributes: Attributes?,
        @SerializedName("id")
        var id: Int?
    ) : Parcelable {
        @Parcelize
        data class Attributes(
            @SerializedName("createdAt")
            var createdAt: String?,
            @SerializedName("publishedAt")
            var publishedAt: String?,
            @SerializedName("start_date")
            var startDate: String?,
            @SerializedName("updatedAt")
            var updatedAt: String?,
            @SerializedName("work_mail")
            var workMail: String?,
            @SerializedName("work_team")
            var workTeam: String?,
            @SerializedName("worker_name")
            var workerName: String?,
            @SerializedName("email_sended")
            var emailSended: Boolean?,
            @SerializedName("on_vacation")
            var onVacation: Boolean?,
            @SerializedName("end_date")
            var endDate : String?
        ) : Parcelable
    }

    data class Meta(
        @SerializedName("pagination")
        var pagination: Pagination?
    ) {
        data class Pagination(
            @SerializedName("page")
            var page: Int?,
            @SerializedName("pageCount")
            var pageCount: Int?,
            @SerializedName("pageSize")
            var pageSize: Int?,
            @SerializedName("total")
            var total: Int?
        )
    }
}