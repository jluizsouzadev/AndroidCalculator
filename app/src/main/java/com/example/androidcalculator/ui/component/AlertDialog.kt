package com.example.androidcalculator.ui.component

import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    showAlertDialog: Boolean,
    title: String = "",
    text: String = "",
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black
) {
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { onDismissRequest },
            buttons = { },
            title = { Text(title) },
            text = { Text(text)},
            shape = AbsoluteRoundedCornerShape(100)
        )
    }
}
