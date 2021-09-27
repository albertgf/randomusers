package com.albertgf.randomusers.features.users

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.albertgf.randomusers.common.models.presentation.UserUi
import com.albertgf.randomusers.common.ui.compose.BasicAppBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: UserDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid = intent.getStringExtra("uid")

        viewModel.loadUser(uid)

        setContent {
            MaterialTheme {
                Screen(viewModel.user.collectAsState())
            }
        }
    }

    @Composable
    fun Screen(user: State<UserUi>) {
        Scaffold(
            topBar = {
                BasicAppBar {
                    finish()
                }
            }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                GlideImage(
                    imageModel = user.value.picture ?: "",
                    requestOptions = RequestOptions().circleCrop(),
                    requestBuilder = Glide.with(applicationContext).asDrawable(),
                    circularReveal = CircularReveal(duration = 250),
                    modifier = Modifier.padding(8.dp)
                )
                Text(text = "${user.value.name} ${user.value.surname}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
                )
                Text(text = user.value.email,
                fontSize = 18.sp)
                Text(text = user.value.gender, fontFamily = FontFamily.Serif,
                fontSize = 18.sp)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 24.dp)
                ) {
                    Text(text = user.value.street )
                    Text(text = user.value.city )
                    Text(text = user.value.state)
                }

                Text(text = user.value.registeredDate, fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth())
            }
        }
    }
}