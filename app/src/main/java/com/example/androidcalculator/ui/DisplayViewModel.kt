package com.example.androidcalculator.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayViewModel : ViewModel() {
    private val sum: String = "+"
    private val _minus: String = "-"
    private val multiple: String = "*"
    private val split: String = "/"
    private val percent: String = "%"
    private val dot: String = "."
    private val equal: String = "="
    private val bracket: String = "()"
    private var tempTopDisplay: MutableList<String> = mutableListOf()
    private val _topDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _bottomDisplay: MutableLiveData<String> = MutableLiveData("")
    private val _backspaceEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val topDisplay: LiveData<String> = _topDisplay
    val bottomDisplay: LiveData<String> = _bottomDisplay
    val backspaceEnabled: LiveData<Boolean> = _backspaceEnabled


    private fun backspaceButtonEnabled(enabled: Boolean) {
        _backspaceEnabled.value = enabled
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
        val counter: Int = tempTopDisplay.count()
        var text: String = number.toString()
        tempTopDisplay.add(text)
        text = ""
        if (counter == 0) {
            text = tempTopDisplay.first()
            _topDisplay.value = text
            return
        }
        for (character in tempTopDisplay) {
            text += character
            _topDisplay.value = text
        }
    }

    fun pressBackspace() {
        var text = ""
        tempTopDisplay.removeLastOrNull()
        val counter = tempTopDisplay.count()
        if (counter == 0) {
            _topDisplay.value = text
            return
        }
        if (counter > 1) {
            for (character in tempTopDisplay) {
                text += character
            }
            _topDisplay.value = text
        } else _topDisplay.value = tempTopDisplay.first()
    }
}