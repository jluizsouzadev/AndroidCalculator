package com.example.androidcalculator.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme
import com.example.androidcalculator.ui.theme.CustomButton
import com.example.androidcalculator.ui.theme.DeleteButton

@Composable
fun MainScreen() {
    val currentOrientation = LocalConfiguration.current.orientation
    if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) Portrait()
    else Landscape()
}

@Preview(showBackground = true)
@Composable
fun Portrait() {
    val rowModifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 6.dp)
                        .fillMaxWidth()
    val rowHorizontalArrangement = Arrangement.SpaceBetween
    AndroidCalculatorTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CompositionLocalProvider(LocalTextInputService provides null) {
                TextField(
                    value = "1000",
                    onValueChange = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    singleLine = false,
                    colors = TextFieldDefaults
                        .textFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.Unspecified,
                            cursorColor = Color.Green,
                            focusedIndicatorColor = Color.Unspecified,
                            unfocusedIndicatorColor = Color.Unspecified
                        )
                )
                TextField(
                    value = "2000",
                    onValueChange = {

                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    colors = TextFieldDefaults
                        .textFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.Unspecified,
                            cursorColor = Color.Green,
                            focusedIndicatorColor = Color.Unspecified,
                            unfocusedIndicatorColor = Color.Unspecified
                        )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    DeleteButton()
                }
                CustomDivider()
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    CustomButton(
                        text = "C",
                        textColor = Color.Red
                    )
                    CustomButton(
                        text = "( )",
                        textColor = Color.Green
                    )
                    CustomButton(
                        text = "%",
                        textColor = Color.Green
                    )
                    CustomButton(
                        text = "÷",
                        textColor = Color.Green
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomButton("7")
                    CustomButton("8")
                    CustomButton("9")
                    CustomButton(
                        text = "x",
                        textColor = Color.Green
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    CustomButton("4")
                    CustomButton("5")
                    CustomButton("6")
                    CustomButton(
                        text = "-",
                        textColor = Color.Green
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    CustomButton("1")
                    CustomButton("2")
                    CustomButton("3")
                    CustomButton(
                        text = "+",
                        textColor = Color.Green
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = rowHorizontalArrangement
                ) {
                    CustomButton("+/-")
                    CustomButton("0")
                    CustomButton(".")
                    CustomButton(
                        text = "=",
                        textColor = Color.Green
                    )
                }
            }
        }
    }
}


@Preview(widthDp = 851, heightDp = 393, showBackground = true)
@Composable
fun Landscape() {
    val textFontSize = 12.sp
    val buttonHeight = 30.dp
    val buttonWidth = 70.dp
    val columnModifier = Modifier.padding(0.dp, 0.dp, 6.dp, 0.dp)
    AndroidCalculatorTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            CompositionLocalProvider(LocalTextInputService provides null) {
                TextField(
                    value = "1000",
                    onValueChange = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    singleLine = false,
                    colors = TextFieldDefaults
                        .textFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.Unspecified,
                            cursorColor = Color.Green,
                            focusedIndicatorColor = Color.Unspecified,
                            unfocusedIndicatorColor = Color.Unspecified
                        )
                )
                TextField(
                    value = "2000",
                    onValueChange = {

                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    colors = TextFieldDefaults
                        .textFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.Unspecified,
                            cursorColor = Color.Green,
                            focusedIndicatorColor = Color.Unspecified,
                            unfocusedIndicatorColor = Color.Unspecified
                        )
                )
                DeleteButton()
                CustomDivider()
                Row {
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "C",
                            textColor = Color.Red,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "( )",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "%",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column {
                        CustomButton(
                            text = "÷",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                }
                Row {
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "7",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "8",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "9",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column {
                        CustomButton(
                            text = "x",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                }
                Row {
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "4",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "5",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "6",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column {
                        CustomButton(
                            text = "-",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                }
                Row {
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "1",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "2",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "3",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column {
                        CustomButton(
                            text = "+",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                }
                Row {
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "+/-",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = "0",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column(modifier = columnModifier) {
                        CustomButton(
                            text = ".",
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                    Column {
                        CustomButton(
                            text = "=",
                            textColor = Color.Green,
                            textFontSize = textFontSize,
                            buttonHeight = buttonHeight,
                            buttonWidth = buttonWidth
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomDivider() {
    Divider(
        color = Color.Gray,
        modifier = Modifier
            .padding(6.dp)
            .height(1.dp)
            .fillMaxWidth()
    )
}

