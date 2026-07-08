package com.crest247.flickarraykeyboard.modes.number

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

sealed interface NumberAction : KeyboardAction {
    data class InputChar(val char: String) : NumberAction, Clickable
}
