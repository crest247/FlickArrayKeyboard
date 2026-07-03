package com.crest247.flickarraykeyboard.modes.english

import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.TriggerOnDownAction

enum class ShiftState {
    LOWERCASE,
    UPPERCASE,
    CAPS_LOCK
}
sealed interface EnglishAction : KeyboardAction {
    data class InputChar(val char: String) : EnglishAction
    object ToggleShift : EnglishAction, TriggerOnDownAction
}
