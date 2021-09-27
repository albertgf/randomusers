package com.albertgf.randomusers

import com.albertgf.randomusers.common.db.User

class TestUtil {
    companion object {
        fun createUser(uid: String) : User {
            return User(
                uid = uid,
                name = "name${uid}",
                surname = "surname${uid}",
                email = "email${uid}",
                thumb = "thumb",
                picture  = "picture",
                phone  = "phone",
                gender = "gender",
                street = "street",
                state = "state",
                city = "city",
                registeredDate = "date"
            )
        }

        fun createUserList(size: Int, different: Boolean) : List<User> {
            val listUser = arrayListOf<User>()
            repeat(size) {
                listUser.add(createUser(if (different) it.toString() else "uid"))
            }
            return listUser
        }
    }
}