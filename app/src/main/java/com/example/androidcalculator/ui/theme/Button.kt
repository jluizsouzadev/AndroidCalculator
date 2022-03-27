package com.example.androidcalculator.ui.theme

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    textColor: Color = Color.Gray,
    backgroundColor: Color = Color.LightGray,
    textFontSize: TextUnit = 20.sp,
    enabledButton: Boolean = true,
    buttonHeight: Dp = 70.dp,
    buttonWidth: Dp = 70.dp
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(buttonHeight)
            .width(buttonWidth),
        enabled = enabledButton,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = textFontSize
        )
    }
}

@Composable
fun DeleteButton() {
    TextButton(
        onClick = { /*TODO*/ },
        enabled = false
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = Color.DarkGray
        )
    }
}

