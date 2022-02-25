package com.example.vacation_manager_android.data_classes


import com.google.gson.annotations.SerializedName

data class WorkersGetResponse(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("meta")
    var meta: Meta?
) {
    data class Data(
        @SerializedName("attributes")
        var attributes: Attributes?,
        @SerializedName("id")
        var id: Int?
    ) {
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
            @SerializedName("end_date")
            var endDate: String?
        )
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