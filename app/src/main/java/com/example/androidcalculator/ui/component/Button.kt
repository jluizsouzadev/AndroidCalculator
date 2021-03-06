package com.example.androidcalculator.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcalculator.R

@Composable
fun CustomButton(
    text: String,
    textColor: Color = Color.Gray,
    backgroundColor: Color = Color.LightGray,
    textFontSize: TextUnit = 20.sp,
    enabledButton: Boolean = true,
    buttonHeight: Dp = 70.dp,
    buttonWidth: Dp = 70.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
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
fun BackspaceButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_backspace_black_24dp),
            contentDescription = null,
            tint = Color.Green
        )
    }
}

