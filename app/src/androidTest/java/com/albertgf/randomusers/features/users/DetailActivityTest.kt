package com.albertgf.randomusers.features.users

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.albertgf.randomusers.TestUtil
import com.albertgf.randomusers.common.db.User
import com.albertgf.randomusers.common.models.mapper.UserMapper
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.ui.theme.RandomUsersTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var user: StateFlow<UserUi>

    @Before
    fun load() = runBlocking {

        user = MutableStateFlow(UserMapper().mapDomainToUi(TestUtil.createUser("uid"))).asStateFlow()
    }

    @Test
    fun check_compose_bind_user_data() {
        composeTestRule.setContent {
            RandomUsersTheme {
                UserScreen(user.collectAsState(), context = ApplicationProvider.getApplicationContext(), onBack = {})
            }
        }

        composeTestRule.onNodeWithText("emailuid").assertExists()
        composeTestRule.onNodeWithText("nameuid surnameuid").assertExists()
        composeTestRule.onNodeWithText("gender").assertExists()
        composeTestRule.onNodeWithText("city").assertExists()
        composeTestRule.onNodeWithText("state").assertExists()
        composeTestRule.onNodeWithText("street").assertExists()
    }
}