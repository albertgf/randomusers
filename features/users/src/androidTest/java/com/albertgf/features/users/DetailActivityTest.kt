package com.albertgf.features.users

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.albertgf.features.users.detail.UserDetailActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {
    private val intent = Intent(ApplicationProvider.getApplicationContext(), UserDetailActivity::class.java)
        .putExtra("uid2", "uid")

    lateinit var scenario: ActivityScenario<UserDetailActivity>

    @Test
    fun finish_activity_with_wrong_or_empty_extra() {
        scenario = ActivityScenario.launch(intent)

        Assert.assertTrue(scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }
}