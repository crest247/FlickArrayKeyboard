package com.crest247.flickarraykeyboard.modes.symbol

import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

sealed interface SymbolAction : KeyboardAction {
    data class InputSymbol(val symbol: String) : SymbolAction, Clickable
    data class SwitchCategory(val index: Int) : SymbolAction, Clickable
}