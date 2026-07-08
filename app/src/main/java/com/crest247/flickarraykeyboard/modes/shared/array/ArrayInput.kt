package com.crest247.flickarraykeyboard.modes.shared.array

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.Repeatable

interface ArrayAction : KeyboardAction {
    data class InputRadical(
        val displayStr: String,
        val lookupStr: String
    ) : ArrayAction, Clickable
    object Backspace : ArrayAction, Clickable, Repeatable
    object Space : ArrayAction, Clickable
    object Enter : ArrayAction, Clickable
}