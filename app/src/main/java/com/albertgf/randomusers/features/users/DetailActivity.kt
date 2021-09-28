package com.albertgf.randomusers.features.users

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import com.albertgf.randomusers.ui.theme.RandomUsersTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: UserDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid = intent.getStringExtra("uid")

        if (uid == null) {
            finish()
            return
        }

        viewModel.loadUser(uid)

        setContent {
            RandomUsersTheme {
                UserScreen(
                    viewModel.user.collectAsState(),
                    context = applicationContext,
                    onBack = { finish() })
            }
        }
    }


}