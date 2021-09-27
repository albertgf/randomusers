package com.albertgf.randomusers.common.models.presentation

data class UserUi (
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
        )