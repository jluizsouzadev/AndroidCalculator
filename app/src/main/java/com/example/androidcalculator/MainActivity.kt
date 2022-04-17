package com.example.androidcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcalculator.ui.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val displayViewModel: DisplayViewModel = viewModel()
            val topDisplay: String by displayViewModel.topDisplay.observeAsState("")
            val backspaceButtonEnabled: Boolean by displayViewModel.backspaceEnabled.observeAsState(
                false
            )
            MainScreen(
                topDisplay = topDisplay,
                backspaceButtonEnabled = backspaceButtonEnabled,
                displayViewModel = displayViewModel
            )
        }
    }
}
