package com.crest247.flickarraykeyboard.modes.number

sealed class NumberAction {
    data class InputChar(val char: String) : NumberAction()
}
