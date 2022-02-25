package com.example.vacation_manager_android.data_classes


import com.google.gson.annotations.SerializedName

data class UserInfoRegister(
    @SerializedName("data")
    var `data`: Data?
) {
    data class Data(
        @SerializedName("password")
        var password: String?,
        @SerializedName("token_app")
        var tokenApp: String?,
        @SerializedName("user_email")
        var userEmail: String?,
        @SerializedName("username")
        var username: String?
    )
}