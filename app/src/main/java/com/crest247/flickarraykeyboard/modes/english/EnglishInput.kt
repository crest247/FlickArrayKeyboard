package com.crest247.flickarraykeyboard.modes.english

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.DownTriggerable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

enum class ShiftState {
    LOWERCASE,
    UPPERCASE,
    CAPS_LOCK
}

sealed interface EnglishAction : KeyboardAction {
    data class InputChar(val char: String) : EnglishAction, Clickable
    object ToggleShift : EnglishAction, DownTriggerable
}
