package com.albertgf.features.users.detail

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.common.coreview.ui.compose.BasicAppBar
import com.albertgf.features.users.models.UserUi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserScreen(user: State<UserUi>, context: Context, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            BasicAppBar {
                onBack()
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
                imageModel = user.value.picture,
                requestOptions = RequestOptions().circleCrop(),
                requestBuilder = Glide.with(context).asDrawable(),
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