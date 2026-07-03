package com.crest247.flickarraykeyboard.modes.arrayFlick

import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.LongPressAction
import com.crest247.flickarraykeyboard.core.models.RepeatableAction
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

sealed interface ArrayFlickAction : ArrayAction {
     data class InputRadical(
        val displayStr: String,
        val lookupStr: String,
        override val longPressAction: KeyboardAction? = null
    ) : ArrayAction, LongPressAction
}