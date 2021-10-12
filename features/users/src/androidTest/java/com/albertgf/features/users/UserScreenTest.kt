package com.albertgf.features.users

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.albertgf.common.coreview.ui.theme.RandomUsersTheme
import com.albertgf.features.users.detail.UserScreen
import com.albertgf.features.users.models.UserUi
import com.albertgf.features.users.models.UserUiMapper
import org.junit.Assert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var user: StateFlow<UserUi>

    @Before
    fun load() = runBlocking {

        user = MutableStateFlow(UserUiMapper().mapDomainToUi(TestDomainUtil.createUser("uid"))).asStateFlow()
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

    @Test
    fun check_appbar_on_back_btn_is_called() {
        var onback = false
        composeTestRule.setContent {
            RandomUsersTheme {
                UserScreen(user.collectAsState(), context = ApplicationProvider.getApplicationContext(), onBack = { onback = true})
            }
        }

        composeTestRule.onNode(hasContentDescription("Previous")).performClick()

        Assert.assertTrue(onback)
    }
}