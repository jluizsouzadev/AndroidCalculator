package com.example.androidcalculator.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcalculator.AlertDialogParameters
import com.example.androidcalculator.DisplayViewModel
import com.example.androidcalculator.ui.component.BackspaceButton
import com.example.androidcalculator.ui.component.CustomAlertDialog
import com.example.androidcalculator.ui.component.CustomButton
import com.example.androidcalculator.ui.theme.AndroidCalculatorTheme
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreen(
    topDisplay: TextFieldValue,
    bottomDisplay: String,
    backspaceButtonEnabled: Boolean,
    alertDialogParameters: AlertDialogParameters,
    displayViewModel: DisplayViewModel
) {
    val currentOrientation = LocalConfiguration.current.orientation
    if (currentOrientation == Configuration.ORIENTATION_PORTRAIT)
        Portrait(
            topDisplay = topDisplay,
            bottomDisplay = bottomDisplay,
            backspaceButtonEnabled = backspaceButtonEnabled,
            alertDialogParameters = alertDialogParameters,
            displayViewModel = displayViewModel
        )
    else
        Landscape(
            topDisplay = topDisplay,
            bottomDisplay = bottomDisplay,
            backspaceButtonEnabled = backspaceButtonEnabled,
            alertDialogParameters = alertDialogParameters,
            displayViewModel = displayViewModel
        )
}

//@Preview(showBackground = true)
@Composable
fun Portrait(
    topDisplay: TextFieldValue,
    bottomDisplay: String,
    backspaceButtonEnabled: Boolean,
    alertDialogParameters: AlertDialogParameters,
    displayViewModel: DisplayViewModel
) {
    val rowModifier = Modifier
        .padding(0.dp, 0.dp, 0.dp, 6.dp)
        .fillMaxWidth()
    val rowHorizontalArrangement = Arrangement.SpaceBetween
    val focusRequester = FocusRequester()
    AndroidCalculatorTheme {
        CustomAlertDialog(
            onDismissRequest = {
                displayViewModel.showAlertDialog(false, "", "")
            },
            showAlertDialog = alertDialogParameters.enabled,
            title = alertDialogParameters.title,
            text = alertDialogParameters.text
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CompositionLocalProvider(LocalTextInputService provides null) {
                BasicTextField(
                    value = topDisplay,
                    onValueChange = { displayViewModel.onTopDisplayChange(it) },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .height(150.dp)
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    singleLine = false
                )
                DisposableEffect(Unit) {
                    focusRequester.requestFocus()
                    onDispose { }
                }
                BasicTextField(
                    value = bottomDisplay,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    singleLine = false
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                BackspaceButton(
                    enabled = backspaceButtonEnabled,
                    onClick = { displayViewModel.pressBackspace() }
                )
            }
            CustomDivider()
            Row(
                modifier = rowModifier,
                horizontalArrangement = rowHorizontalArrangement
            ) {
                CustomButton(
                    text = "C",
                    textColor = Color.Red,
                    onClick = { displayViewModel.pressClear() }
                )
                CustomButton(
                    text = "( )",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressBracket() }
                )
                CustomButton(
                    text = "%",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressPercent() }
                )
                CustomButton(
                    text = "??",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressSplit() }
                )
            }
            Row(
                modifier = rowModifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    text = "7",
                    onClick = { displayViewModel.pressNumber(7) }
                )
                CustomButton(
                    text = "8",
                    onClick = { displayViewModel.pressNumber(8) }
                )
                CustomButton(
                    text = "9",
                    onClick = { displayViewModel.pressNumber(9) }
                )
                CustomButton(
                    text = "x",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressMultiple() }
                )
            }
            Row(
                modifier = rowModifier,
                horizontalArrangement = rowHorizontalArrangement
            ) {
                CustomButton(
                    text = "4",
                    onClick = { displayViewModel.pressNumber(4) }
                )
                CustomButton(
                    text = "5",
                    onClick = { displayViewModel.pressNumber(5) }
                )
                CustomButton(
                    text = "6",
                    onClick = { displayViewModel.pressNumber(6) }
                )
                CustomButton(
                    text = "-",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressMinus() }
                )
            }
            Row(
                modifier = rowModifier,
                horizontalArrangement = rowHorizontalArrangement
            ) {
                CustomButton(
                    text = "1",
                    onClick = { displayViewModel.pressNumber(1) }
                )
                CustomButton(
                    text = "2",
                    onClick = { displayViewModel.pressNumber(2) }
                )
                CustomButton(
                    text = "3",
                    onClick = { displayViewModel.pressNumber(3) }
                )
                CustomButton(
                    text = "+",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressSum() }
                )
            }
            Row(
                modifier = rowModifier,
                horizontalArrangement = rowHorizontalArrangement
            ) {
                CustomButton(
                    text = "+/-",
                    onClick = { displayViewModel.pressPositiveAndNegative() }
                )
                CustomButton(
                    text = "0",
                    onClick = { displayViewModel.pressNumber(0) }
                )
                CustomButton(
                    text = ".",
                    onClick = { displayViewModel.pressDot() }
                )
                CustomButton(
                    text = "=",
                    textColor = Color.Green,
                    onClick = { displayViewModel.pressEqual() }
                )
            }
        }
    }
}

//@Preview(widthDp = 851, heightDp = 393, showBackground = true)
@Composable
fun Landscape(
    topDisplay: TextFieldValue,
    bottomDisplay: String,
    backspaceButtonEnabled: Boolean,
    alertDialogParameters: AlertDialogParameters,
    displayViewModel: DisplayViewModel
) {
    val textFontSize = 10.sp
    val buttonHeight = 30.dp
    val buttonWidth = 70.dp
    val columnModifier = Modifier.padding(0.dp, 0.dp, 6.dp, 0.dp)
    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false
    val focusRequester = FocusRequester()
    AndroidCalculatorTheme {
        CustomAlertDialog(
            onDismissRequest = {
                displayViewModel.showAlertDialog(false, "", "")
            },
            showAlertDialog = alertDialogParameters.enabled,
            title = alertDialogParameters.title,
            text = alertDialogParameters.text
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.End
        ) {
            CompositionLocalProvider(LocalTextInputService provides null) {
                BasicTextField(
                    value = topDisplay,
                    onValueChange = { },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    singleLine = false
                )
                DisposableEffect(Unit) {
                    focusRequester.requestFocus()
                    onDispose { }
                }
                BasicTextField(
                    value = bottomDisplay,
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                    singleLine = false
                )
            }
            BackspaceButton(
                enabled = backspaceButtonEnabled,
                onClick = { displayViewModel.pressBackspace() }
            )
            CustomDivider()
            Row {
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "C",
                        textColor = Color.Red,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressClear() }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "( )",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressBracket() }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "%",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressBracket() }
                    )
                }
                Column {
                    CustomButton(
                        text = "??",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressSplit() }
                    )
                }
            }
            Row {
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "7",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(7) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "8",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(8) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "9",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(9) }
                    )
                }
                Column {
                    CustomButton(
                        text = "x",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressMultiple() }
                    )
                }
            }
            Row {
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "4",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(4) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "5",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(5) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "6",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(6) }
                    )
                }
                Column {
                    CustomButton(
                        text = "-",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressMinus() }
                    )
                }
            }
            Row {
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "1",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(1) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "2",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(2) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "3",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(3) }
                    )
                }
                Column {
                    CustomButton(
                        text = "+",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressSum() }
                    )
                }
            }
            Row {
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "+/-",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressPositiveAndNegative() }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = "0",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressNumber(0) }
                    )
                }
                Column(modifier = columnModifier) {
                    CustomButton(
                        text = ".",
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressDot() }
                    )
                }
                Column {
                    CustomButton(
                        text = "=",
                        textColor = Color.Green,
                        textFontSize = textFontSize,
                        buttonHeight = buttonHeight,
                        buttonWidth = buttonWidth,
                        onClick = { displayViewModel.pressEqual() }
                    )
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

