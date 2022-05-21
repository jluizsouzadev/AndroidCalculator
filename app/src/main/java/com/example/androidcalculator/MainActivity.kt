package com.example.androidcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcalculator.ui.screen.MainScreen
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val displayViewModel: DisplayViewModel = viewModel()
            val topDisplay: TextFieldValue
                    by displayViewModel.topDisplay.observeAsState(TextFieldValue())
            val bottomDisplay: String by displayViewModel.bottomDisplay.observeAsState("")
            val backspaceButtonEnabled: Boolean by displayViewModel.backspaceEnabled.observeAsState(
                false
            )
            val AlertDialogParameters: AlertDialogParameters
                    by displayViewModel.alertDialogParameters.observeAsState(
                        AlertDialogParameters(false, "", "")
                    )
            MainScreen(
                topDisplay = topDisplay,
                bottomDisplay = bottomDisplay,
                backspaceButtonEnabled = backspaceButtonEnabled,
                alertDialogParameters = AlertDialogParameters,
                displayViewModel = displayViewModel
            )
        }
    }
}
