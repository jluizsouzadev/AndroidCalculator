package com.example.androidcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayViewModel : ViewModel() {
    private var tempTopDisplay: MutableList<Any> = mutableListOf()
    private var tempBottomDisplay: MutableList<Operand> = mutableListOf()
    private val _topDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _bottomDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _backspaceEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val topDisplay: LiveData<String> = _topDisplay
    val bottomDisplay: LiveData<String> = _bottomDisplay
    val backspaceEnabled: LiveData<Boolean> = _backspaceEnabled


    private fun backspaceButtonEnabled(enabled: Boolean) {
        _backspaceEnabled.value = enabled
    }

    private fun updateDisplays(
        _tempTopDisplay: MutableList<Any>,
        _tempBottomDisplay: MutableList<Operand>
    ) {
        val empty = _tempTopDisplay.isEmpty()
        if (empty) {
            _topDisplay.value = ""
            _bottomDisplay.value = ""
            return
        }
        val counter = _tempTopDisplay.count()
        if (counter == 1) {
            val operand = _tempTopDisplay.first() as Operand
            val text = operand.value.toString()
            _topDisplay.value = text
            _bottomDisplay.value = ""
            return
        }
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
        val empty = tempTopDisplay.isEmpty()
        if (empty) {
            val operand = Operand(number.toLong())
            tempTopDisplay.add(operand)
            //

            return
        }
        val lastItem = tempTopDisplay.last()
        if (lastItem is Operand) {
            var digits = lastItem.value.toString()
            digits += number.toString()
            lastItem.value = digits.toLong()
            val lastIndex = tempTopDisplay.lastIndex
            tempTopDisplay.set(lastIndex, lastItem)

        }

//        val counter: Int = tempTopDisplay.count()
//        var text: String = number.toString()
//        tempTopDisplay.add(text)
//        text = ""
//        if (counter == 0) {
//            text = tempTopDisplay.first()
//            _topDisplay.value = text
//            return
//        }
//        for (character in tempTopDisplay) {
//            text += character
//            _topDisplay.value = text
//        }
    }

    fun pressBackspace() {
//        var text = ""
//        tempTopDisplay.removeLastOrNull()
//        val counter = tempTopDisplay.count()
//        if (counter == 0) {
//            _topDisplay.value = text
//            return
//        }
//        if (counter > 1) {
//            for (character in tempTopDisplay) {
//                text += character
//            }
//            _topDisplay.value = text
//        } else {
//            text = tempTopDisplay.first()
//            _topDisplay.value = text
//        }
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
}
