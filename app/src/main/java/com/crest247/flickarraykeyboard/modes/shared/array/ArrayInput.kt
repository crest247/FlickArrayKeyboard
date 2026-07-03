package com.crest247.flickarraykeyboard.modes.shared.array

import com.crest247.flickarraykeyboard.core.KeyboardAction

sealed interface ArrayAction : KeyboardAction {
    data class InputRadical(val displayStr: String, val lookupStr: String) : ArrayAction
    object Backspace : ArrayAction
    object Space : ArrayAction
    object Enter : ArrayAction
}