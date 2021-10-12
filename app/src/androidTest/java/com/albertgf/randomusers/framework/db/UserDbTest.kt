package com.albertgf.randomusers.framework.db

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

        val users = db.users().findFilteredTest(user.name)

        assertThat(users[0].name, equalTo(user.name))
    }

    @Test
    @Throws(Exception::class)
    fun write_exact_users_and_get_unique_user() {
        val users = TestUtil.createUserList(3, different = false)

        db.users().insertAll(users)

        val usersDb = db.users().findFilteredTest(users[0].name)

        assert(usersDb.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun delete_user_and_not_shown_again() {
        val size = 3
        val users = TestUtil.createUserList(size, different = true)

        db.users().insertAll(users)

        db.users().deleteUser(users[0].uid)

        val usersSize = db.users().getAllTest().size

        assert(usersSize == 2)
    }

    @Test
    @Throws(Exception::class)
    fun find_user_by_email() {
        val size = 3
        val users = TestUtil.createUserList(size, different = true)

        db.users().insertAll(users)

        val usersDb = db.users().findFilteredTest("email2")

        assert(usersDb.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun find_user_by_name() {
        val size = 3
        val users = TestUtil.createUserList(size, different = true)

        db.users().insertAll(users)

        val usersDb = db.users().findFilteredTest("name1")

        assert(usersDb.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun find_user_by_surname() {
        val size = 3
        val users = TestUtil.createUserList(size, different = true)

        db.users().insertAll(users)

        val usersDb = db.users().findFilteredTest("surname0")

        assert(usersDb.size == 1)
    }
}