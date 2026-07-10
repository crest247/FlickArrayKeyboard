package com.crest247.flickarraykeyboard.modes.number

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.LongPressable
import com.crest247.flickarraykeyboard.core.models.UpTriggerable

sealed interface NumberAction : KeyboardAction {
    data class InputChar(val char: String) : NumberAction, UpTriggerable
    data class InputSymbolUp(val symbol: String) : NumberAction, UpTriggerable
    data class InputSymbolClick(val symbol: String) : NumberAction, Clickable
}
