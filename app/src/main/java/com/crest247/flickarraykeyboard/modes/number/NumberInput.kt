package com.crest247.flickarraykeyboard.modes.number

sealed interface NumberAction {
    data class InputChar(val char: String) : NumberAction
}
