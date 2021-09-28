package com.albertgf.randomusers.common.ui.compose

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.albertgf.randomusers.R

@Composable
fun BasicAppBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
        },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.previous),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}