package com.example.androidcalculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class DisplayViewModel : ViewModel() {
    private var tempTopDisplay: MutableList<DisplayItem> = mutableListOf()
    private var tempBottomDisplay: MutableList<String> = mutableListOf()
    private val _topDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _bottomDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _backspaceEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val topDisplay: LiveData<String> = _topDisplay
    val bottomDisplay: LiveData<String> = _bottomDisplay
    val backspaceEnabled: LiveData<Boolean> = _backspaceEnabled


    private fun backspaceButtonEnabled(enabled: Boolean) {
        _backspaceEnabled.value = enabled
    }

    private fun updateDisplays() {
        val isEmpty = tempTopDisplay.isEmpty()
        val empty = ""
        if (isEmpty) {
            _topDisplay.value = empty
            _bottomDisplay.value = empty
            return
        }
        var counter = tempTopDisplay.count()
        if (counter == 1) {
            val firstDisplayItem: DisplayItem = tempTopDisplay.first()
            val digits = firstDisplayItem.text
            _topDisplay.value = digits
            _bottomDisplay.value = empty
            return
        } else {
            var expression = "1 + 1 + 1"
            var result = ExpressionBuilder(expression).build().evaluate()
            _topDisplay.value = result.toString()
            Log.d("Expression:", "Result: $result")
        }
        //        var expression = "1 + 1 + 1"
//        var result = ExpressionBuilder(expression).build().evaluate()
//        _topDisplay.value = result.toInt().toString()
//        Log.d("Expression:", "Result: $result")
    }

    fun onTopDisplayChange(text: String) {
        _topDisplay.value = text
    }

    fun onBottomDisplayChange(text: String) {
        _bottomDisplay.value = text
    }

    fun pressNumber(number: Int) {
        if (number !in 0..9) return
        backspaceButtonEnabled(true)
        val isEmpty = tempTopDisplay.isEmpty()
        if (isEmpty) {
            val newDisplayItem = DisplayItem(
                text = number.toString(),
                displayItemType = DisplayItemType.Operand
            )
            tempTopDisplay.add(newDisplayItem)
            updateDisplays()
            return
        }
        val lastDisplayItem = tempTopDisplay.last()
        val displayItemType = lastDisplayItem.displayItemType
        if (displayItemType == DisplayItemType.Operand) {
            var digits = lastDisplayItem.text
            digits += number.toString()
            val lastIndex = tempTopDisplay.lastIndex
            tempTopDisplay[lastIndex].text = digits
            _topDisplay.value = tempTopDisplay[lastIndex].text
            updateDisplays()
            return
        } else {
            val newDisplayItem = DisplayItem(
                text = number.toString(),
                displayItemType = DisplayItemType.Operand
            )
            tempTopDisplay.add(newDisplayItem)
            updateDisplays()
            return
        }
    }

    fun pressBackspace() {
        val isEmpty = tempTopDisplay.isEmpty()
        if (isEmpty) return
        val counter = tempTopDisplay.count()
        val lastDisplayItem: DisplayItem = tempTopDisplay.last()
        val displayItemType: DisplayItemType = lastDisplayItem.displayItemType
        if (displayItemType == DisplayItemType.Operator) {
            tempTopDisplay.removeLast()
            updateDisplays()
            return
        } else {
            var operand = lastDisplayItem.text
            operand = operand.dropLast(1)
            val lastIndex = tempTopDisplay.lastIndex
            tempTopDisplay[lastIndex].text = operand
            updateDisplays()
        }
    }

    fun pressSum() {
        val isEmpty = tempTopDisplay.isEmpty()
        if (isEmpty) return
        val counter = tempTopDisplay.count()
        if (counter == 1) {
            val lastDisplayItem: DisplayItem = tempTopDisplay.last()
            val type = lastDisplayItem.displayItemType
            val operator = lastDisplayItem.text
            if ((type == DisplayItemType.Operator) and
                (operator == OperatorType.LEFT_BRACKET.signal)
            ) {
                val newDisplayItem: DisplayItem = DisplayItem(
                    OperatorType.ADDITION.signal,
                    DisplayItemType.Operator
                )
                tempTopDisplay.add(newDisplayItem)
                updateDisplays()
                return
            }
        } else {
            val lastDisplayItem: DisplayItem = tempTopDisplay.last()
            val displayItemType = lastDisplayItem.displayItemType
            if (displayItemType == DisplayItemType.Operator) {
                val operator = lastDisplayItem.text
                when (operator) {
                    OperatorType.ADDITION.signal -> return
                    OperatorType.SUBTRACTION.signal -> {
                        lastDisplayItem.text = OperatorType.ADDITION.signal
                        updateDisplays()
                        return
                    }
                    OperatorType.MULTIPLICATION.signal -> {
                        lastDisplayItem.text = OperatorType.ADDITION.signal
                        updateDisplays()
                        return
                    }
                    OperatorType.DIVISION.signal -> {
                        lastDisplayItem.text = OperatorType.ADDITION.signal
                        updateDisplays()
                        return
                    }
                    OperatorType.PERCENT.signal -> {
                        val newDisplayItem: DisplayItem = DisplayItem(
                            OperatorType.ADDITION.signal,
                            DisplayItemType.Operator
                        )
                        tempTopDisplay.add(newDisplayItem)
                        updateDisplays()
                        return
                    }
                    OperatorType.LEFT_BRACKET.signal -> {
                        val newDisplayItem: DisplayItem = DisplayItem(
                            OperatorType.ADDITION.signal,
                            DisplayItemType.Operator
                        )
                        tempTopDisplay.add(newDisplayItem)
                        updateDisplays()
                        return
                    }
                    OperatorType.RIGHT_BRACKET.signal -> {
                        val newDisplayItem: DisplayItem = DisplayItem(
                            OperatorType.ADDITION.signal,
                            DisplayItemType.Operator
                        )
                        tempTopDisplay.add(newDisplayItem)
                        return
                    }
                }
            }
        }
    }

    fun pressMinus() {

    }

    fun pressMultiple() {

    }

    fun pressSplit() {

    }

    fun pressPercent() {

    }

    fun pressDot() {

    }

    fun pressEqual() {

    }

    fun pressBracket() {

    }

    fun pressClear() {
        val empty = ""
        tempTopDisplay.clear()
        tempBottomDisplay.clear()
        _topDisplay.value = empty
        _bottomDisplay.value = empty
    }

    fun pressPositiveAndNegative() {
        TODO("Not yet implemented")
    }
}
