package com.example.androidcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayViewModel : ViewModel() {
    private var operator = Operator()
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
        val empty = tempTopDisplay.isEmpty()
        if (empty) {
            _topDisplay.value = ""
            _bottomDisplay.value = ""
            return
        }
        val counter = tempTopDisplay.count()
        if (counter == 1) {
            val firstDisplayItem: DisplayItem = tempTopDisplay.first()
            val digits = firstDisplayItem.text
            _topDisplay.value = digits
            _bottomDisplay.value = ""
            return
        }
        if (counter == 2) {
            val second = 1
            val firstDisplayItem: DisplayItem = tempTopDisplay.first()
            val secondDisplayItem: DisplayItem = tempTopDisplay[second]
            val digits = firstDisplayItem.text + secondDisplayItem.text
            _topDisplay.value = digits
            _bottomDisplay.value = ""
            return
        }
    }

//        } else {
//            var result: Long = 0
//            var operation = ""
//            for (displayItem in tempTopDisplay) {
//                val tempDisplayItem: DisplayItem = displayItem
//                if (tempDisplayItem.displayItemType == DisplayItemType.Operand) {
//                    if (tempDisplayItem.text == "+") operation == "+"
//                    if (tempDisplayItem.text == "-") operation == "-"
//                    if (tempDisplayItem.text == "x") operation == "x"
//                    if (tempDisplayItem.text == "÷") operation == "÷"
//                    if (tempDisplayItem.text == "%") operation == "%"
//                    if (tempDisplayItem.text == "=") operation == "="
//                } else {
//                    if (operation == "+") {
//                        result += tempDisplayItem.text.toLong()
//                        val text = result.toString()
//                        _bottomDisplay.value = text
//                    }
//                    if (operation == "-") {
//                        result -= tempDisplayItem.text.toLong()
//                        tempTopDisplay.clear()
//                        val text = result.toString()
//                        val newDisplayItem = DisplayItem(
//                            text = text,
//                            displayItemType = DisplayItemType.Operand
//                        )
//                        tempTopDisplay.add(newDisplayItem)
//                        return
//                    }
//                    if (operation == "x") {
//                        result *= tempDisplayItem.text.toLong()
//                        tempTopDisplay.clear()
//                        val text = result.toString()
//                        val newDisplayItem = DisplayItem(
//                            text = text,
//                            displayItemType = DisplayItemType.Operand
//                        )
//                        tempTopDisplay.add(newDisplayItem)
//                        return
//                    }
//                    if (operation == "÷") {
//                        result /= tempDisplayItem.text.toLong()
//                        tempTopDisplay.clear()
//                        val text = result.toString()
//                        val newDisplayItem = DisplayItem(
//                            text = text,
//                            displayItemType = DisplayItemType.Operand
//                        )
//                        tempTopDisplay.add(newDisplayItem)
//                        return
//                    }
//                    if (operation == "%") result += tempDisplayItem.text.toLong()
//                    if (operation == "=") {

//                    }
//                }
//            }
//        }
//    }

    fun onTopDisplayChange(text: String) {
        _topDisplay.value = text
    }

    fun onBottomDisplayChange(text: String) {
        _bottomDisplay.value = text
    }

    fun pressNumber(number: Int) {
        if (number !in 0..9) return
        backspaceButtonEnabled(true)
        val empty = tempTopDisplay.isEmpty()
        if (empty) {
            val displayItem = DisplayItem(
                text = number.toString(),
                displayItemType = DisplayItemType.Operand
            )
            tempTopDisplay.add(displayItem)
            updateDisplays()
            return
        }
        val lastDisplayItem: DisplayItem = tempTopDisplay.last()
        val type = lastDisplayItem.displayItemType
        if (type == DisplayItemType.Operand) {
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
        val empty = tempTopDisplay.isEmpty()
        if (empty) return
        val lastDisplayItem: DisplayItem = tempTopDisplay.last()
        val type = lastDisplayItem.displayItemType
        if (type == DisplayItemType.Operator) {
            tempTopDisplay.removeLast()
            updateDisplays()
            return
        } else {
            var digits = lastDisplayItem.text
            digits = digits.dropLast(1)
            val lastIndex = tempTopDisplay.lastIndex
            tempTopDisplay[lastIndex].text = digits
            updateDisplays()
        }
    }

    fun pressSum() {
//        val counter = tempTopDisplay.count()
//        val lastDigit = tempTopDisplay.lastOrNull()
//        if (counter == 0 || lastDigit == operators.sum) return
//        tempTopDisplay.add(operators.sum)
//        var text = ""
//        for (digit in tempTopDisplay) text += digit
//        _topDisplay.value = text
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
        tempTopDisplay.clear()
        tempBottomDisplay.clear()
        _topDisplay.value = ""
        _bottomDisplay.value = ""
    }
}
