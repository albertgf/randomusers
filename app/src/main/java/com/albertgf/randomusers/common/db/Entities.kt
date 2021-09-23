package com.albertgf.randomusers.common.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = arrayOf(Index(value = ["uid"], unique = true)))
data class User(
    @PrimaryKey(autoGenerate = true) val iid: Int = 0,
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
    val registeredDate: String,
    var deleted: Boolean = false
)