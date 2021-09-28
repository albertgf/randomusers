package com.albertgf.randomusers.common.coreview.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.albertgf.randomusers.R

@Composable
fun UserInputText(input: String, onInputChange: (String) -> Unit) {
    TextField(
        value = input,
        onValueChange = { textValue -> onInputChange(textValue) },
        label = { Text(stringResource(id = R.string.hint_search)) },
        maxLines = 1,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier.fillMaxWidth()
    )
}