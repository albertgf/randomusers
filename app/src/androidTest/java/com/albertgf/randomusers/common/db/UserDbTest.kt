package com.albertgf.randomusers.common.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.albertgf.randomusers.TestUtil
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class UserDbTest {
    private lateinit var db: UserDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDb::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun write_user_and_read_list_by_name() {
        val user = TestUtil.createUser("uid")

        db.users().insertAll(listOf(user))

        val users = db.users().findByName(user.name)

        assertThat(users[0].name, equalTo(user.name))
    }

    @Test
    @Throws(Exception::class)
    fun write_exact_users_and_get_unique_user() {
        val users = TestUtil.createUserList(3, different = false)

        db.users().insertAll(users)

        val usersDb = db.users().findByName(users[0].name)

        assert(usersDb.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun write_different_users_and_get_same_amount() {
        val size = 3
        val users = TestUtil.createUserList(size, different = true)

        db.users().insertAll(users)

        val usersDb = db.users().findByName(users[0].name)

        assert(usersDb.size == size)
    }

    @Test
    @Throws(Exception::class)
    fun delete_user_and_not_shown_again() {
        val size = 3
        val users = TestUtil.createUserList(size, different = true)

        db.users().insertAll(users)

        val usersDb = db.users().findByName(users[0].name)
        usersDb[0].deleted = true

        db.users().deleteUserFromQuery(usersDb[0])

        val usersSize = db.users().getCount()

        assert(usersSize == 2)
    }
}