package com.example.vacation_manager_android.data_classes


import com.google.gson.annotations.SerializedName

data class UserGetResponse(
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
            @SerializedName("password")
            var password: String?,
            @SerializedName("publishedAt")
            var publishedAt: String?,
            @SerializedName("token_app")
            var tokenApp: String?,
            @SerializedName("updatedAt")
            var updatedAt: String?,
            @SerializedName("user_email")
            var userEmail: String?,
            @SerializedName("username")
            var username: String?
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