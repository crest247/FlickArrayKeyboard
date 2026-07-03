package com.crest247.flickarraykeyboard.modes.shared.array

import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.RepeatableAction

interface ArrayAction : KeyboardAction {
    data class InputRadical(val displayStr: String, val lookupStr: String) : ArrayAction
    object Backspace : ArrayAction, RepeatableAction
    object Space : ArrayAction
    object Enter : ArrayAction
}