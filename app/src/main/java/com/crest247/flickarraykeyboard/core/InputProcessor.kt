package com.crest247.flickarraykeyboard.core

sealed class ChineseAction {
    data class InputZhuyin(val zhuyin: String) : ChineseAction()
    object Backspace : ChineseAction()
}

interface InputProcessor<T> {
    fun onAction(action: T)
}

