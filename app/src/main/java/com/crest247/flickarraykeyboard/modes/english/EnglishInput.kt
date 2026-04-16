package com.crest247.flickarraykeyboard.modes.english

enum class ShiftState {
    LOWERCASE,
    UPPERCASE,
    CAPS_LOCK
}
sealed class EnglishAction {
    data class InputChar(val char: String) : EnglishAction()
    object ToggleShift : EnglishAction()
}
