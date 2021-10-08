package com.albertgf.core.domain

data class User(
    val uid: String,
    val name: String,
    val surname: String,
    val email: String,
    val thumb: String,
    val picture: String,
    val phone: String,
    val gender: String,
    val street: String,
    val city: String,
    val state: String,
    val registeredDate: String,
    var deleted: Boolean = false
)