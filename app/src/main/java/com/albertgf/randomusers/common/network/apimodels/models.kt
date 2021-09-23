package com.albertgf.randomusers.common.network.apimodels

import android.util.Log
import com.google.gson.annotations.SerializedName

data class ResultsApi(
    @SerializedName("results") val results: List<UserApi>
)

data class UserApi(
    @SerializedName("id") val id: IdApi,
    @SerializedName("name") val name: NameApi,
    @SerializedName("gender") val gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("location") val location: LocationAPI,
    @SerializedName("phone") val phone: String,
    @SerializedName("picture") val picture: PictureApi,
    @SerializedName("registered") val registered: RegisteredApi,
    @SerializedName("login") val login: LoginApi
)

data class NameApi(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class IdApi(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)

data class LocationAPI(
    @SerializedName("street") val street: StreetApi,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("postcode") val zip: String
)

data class StreetApi(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String
)

data class RegisteredApi(
    @SerializedName("date") val date: String
)

data class PictureApi(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumb: String
)

data class LoginApi(
    @SerializedName("uuid") val uuid: String
)