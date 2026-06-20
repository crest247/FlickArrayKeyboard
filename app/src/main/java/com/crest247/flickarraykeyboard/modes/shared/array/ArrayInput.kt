package com.crest247.flickarraykeyboard.modes.shared.array

sealed interface ArrayAction {
    data class Input(val displayStr: String, val lookupStr: String) : ArrayAction
    object Backspace : ArrayAction
    object Space : ArrayAction
    object Enter : ArrayAction
}