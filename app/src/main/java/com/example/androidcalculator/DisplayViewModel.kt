package com.example.androidcalculator

import android.util.Log
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayViewModel : ViewModel() {
    private var tempBottomDisplay: MutableList<String> = mutableListOf()
    private val _topDisplay: MutableLiveData<TextFieldValue> = MutableLiveData(TextFieldValue())
    private val _bottomDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _backspaceEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _alertDialogParameters: MutableLiveData<AlertDialogParameters> = MutableLiveData(
        AlertDialogParameters(false, "", "")
    )
    val alertDialogParameters: LiveData<AlertDialogParameters> = _alertDialogParameters
    val topDisplay: LiveData<TextFieldValue> = _topDisplay
    val bottomDisplay: LiveData<String> = _bottomDisplay
    val backspaceEnabled: LiveData<Boolean> = _backspaceEnabled

    private fun backspaceButtonEnabled(enabled: Boolean) {
        _backspaceEnabled.value = enabled
    }

    private fun isMaxDigits(mathExpression: String): Boolean {
        val maxDigits = 15
        var counter = 1
        val mathExpressionLength = mathExpression.length - 1
        val dotDecimal: Char = "."[0]
        for (index in 0..mathExpressionLength) {
            if ((mathExpression[index].isDigit()) || (mathExpression[index] == dotDecimal)) {
                counter++
                if (counter > maxDigits) return true
            } else counter = 0
        }
        return false
    }

    fun onTopDisplayChange(newValue: TextFieldValue) {
        _topDisplay.value = newValue
    }

    fun onBottomDisplayChange(newValue: String) {
        _bottomDisplay.value = newValue
    }

    fun showAlertDialog(_enabled: Boolean, _title: String, _text: String) {
        if (_enabled) {
            _alertDialogParameters.value?.apply {
                enabled = _enabled
                title = _title
                text = _text
            }
//            Log.d("alertDialogProprieties.enabled",
//                alertDialogParameters.value?.enabled.toString())
//            Log.d("alertDialogProprieties.title",
//                alertDialogParameters.value?.title.toString())
//            Log.d("alertDialogProprieties.text",
//                alertDialogParameters.value?.text.toString())
            return
        }
        val empty = ""
        _alertDialogParameters.value?.apply {
            enabled = _enabled
            title = empty
            text = empty
        }
//        Log.d("alertDialogProprieties.enabled",
//            alertDialogParameters.value?.enabled.toString())
//        Log.d("alertDialogProprieties.title",
//            alertDialogParameters.value?.title.toString())
//        Log.d("alertDialogProprieties.text",
//            alertDialogParameters.value?.text.toString())
    }

    fun pressNumber(number: Int) {
        if (number !in 0..9) return
        backspaceButtonEnabled(true)
        val isEmpty = _topDisplay.value?.text?.isEmpty()
        if (isEmpty == true) {
            val newTextFieldValue = TextFieldValue(
                text = number.toString(),
                selection = TextRange(number.toString().length)
            )
            _topDisplay.value = newTextFieldValue
            return
        }
        val _isMaxDigits = isMaxDigits(_topDisplay.value?.text as String)
        if (_isMaxDigits) {
//            Log.d("alertDialogParameters.value :", alertDialogParameters.value?.enabled.toString())
            Log.d("_isMaxDigits Value: ", _isMaxDigits.toString())
            showAlertDialog(true, "", "Can't enter more than 15 digits.")
            Log.d("alertDialogParameters.value :", alertDialogParameters.value?.enabled.toString())
            return
        }
        val startIndex = _topDisplay.value?.selection?.start as Int
        val endIndex = startIndex
        val cursorPosition = (_topDisplay.value?.selection?.start as Int) + 1
        val newText = _topDisplay.value?.text?.replaceRange(
            startIndex,
            endIndex,
            number.toString()
        ) as String
        val newTextFieldValue = TextFieldValue(
            text = newText,
            selection = TextRange(cursorPosition)
        )
        _topDisplay.value = newTextFieldValue
    }

    fun pressBackspace() {
        var isEmpty = _topDisplay.value?.text?.isEmpty()
        if (isEmpty == true) return
        val isCollapsed = _topDisplay.value?.selection?.collapsed
        if (isCollapsed == true) {
            val currentCursorPosition = _topDisplay.value?.selection?.start
            if (currentCursorPosition == 0) return
            val startIndex = (_topDisplay.value?.selection?.start as Int) - 1
//            Log.d("startIndex Value: ", startIndex.toString())
            val endIndex = _topDisplay.value?.selection?.end as Int
//            Log.d("endIndex Value: ", endIndex.toString())
            val cursorPosition = startIndex
            val newTextFieldValue = TextFieldValue(
                text = _topDisplay.value?.text?.removeRange(startIndex, endIndex) as String,
                selection = TextRange(cursorPosition)
            )
            _topDisplay.value = newTextFieldValue
            return
        } else {
            val startIndex = _topDisplay.value?.selection?.start as Int
//            Log.d("startIndex Value: ", startIndex.toString())
            val endIndex = _topDisplay.value?.selection?.end as Int
//            Log.d("endIndex Value: ", endIndex.toString())
            val newTextFieldValue = TextFieldValue(
                text = _topDisplay.value?.text?.removeRange(startIndex, endIndex) as String
            )
            _topDisplay.value = newTextFieldValue
        }
        isEmpty = _topDisplay.value?.text?.isEmpty()
        if (isEmpty == true) backspaceButtonEnabled(false)
    }

    fun pressSum() {

    }

    fun pressMinus() {
//        var expression = "1.100001 + 1 + 1"
//        var result = ExpressionBuilder(expression).build().evaluate()
//        _topDisplay.value = result.toString()
//        Log.d("Expression:", "Result: $result")
    }

    fun pressMultiple() {

    }

    fun pressSplit() {

    }

    fun pressPercent() {

    }

    fun pressDot() {
        val isEmpty = _topDisplay.value?.text?.isEmpty()
        val dotSignal = "."
        if (isEmpty == true) {
            val newTextFieldValue = TextFieldValue(text = dotSignal)
            _topDisplay.value = newTextFieldValue
            return
        }
        val _isMaxDigits = isMaxDigits(_topDisplay.value?.text as String)
        if (_isMaxDigits) {
            Log.d("_isMaxDigits Value: ", _isMaxDigits.toString())
        }
        val startIndex = _topDisplay.value?.selection?.start as Int
        val endIndex = startIndex
        val cursorPosition = (_topDisplay.value?.selection?.start as Int) + 1
        val newText = _topDisplay.value?.text?.replaceRange(
            startIndex,
            endIndex,
            dotSignal
        ) as String
        val newTextFieldValue = TextFieldValue(
            text = newText,
            selection = TextRange(cursorPosition)
        )
        _topDisplay.value = newTextFieldValue
    }

    fun pressEqual() {

    }

    fun pressBracket() {

    }

    fun pressClear() {
        val newTextFieldValue = TextFieldValue(text = "")
        _topDisplay.value = newTextFieldValue
        backspaceButtonEnabled(false)
    }

    fun pressPositiveAndNegative() {

    }
}
