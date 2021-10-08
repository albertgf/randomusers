package com.albertgf.features.users.models

data class UserUiMinimal(
    val uid: String,
    val name: String,
    val surname: String,
    val email: String,
    val picture: String,
    val phone: String
)


data class UserUi(
    val uid: String,
    val name: String,
    val surname: String,
    val email: String,
    val picture: String,
    val phone: String,
    val gender: String,
    val street: String,
    val city: String,
    val state: String,
    val registeredDate: String
) {
    companion object {
        fun blank(): UserUi {
            return UserUi(
                uid = "",
                name = "",
                surname = "",
                email = "",
                picture = "",
                phone = "",
                gender = "",
                street = "",
                city = "",
                state = "",
                registeredDate = ""
            )
        }
    }
}