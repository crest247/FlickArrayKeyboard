package com.crest247.flickarraykeyboard.modes.symbol

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

class SymbolProcessor : InputProcessor {
    private var inputConnection: InputConnection? = null
    var editorInfo: EditorInfo? = null; private set

    override fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {
        this.inputConnection = inputConnection
        this.editorInfo = editorInfo
    }
    var currentCategoryIndex by mutableIntStateOf(0)
        private set

    override fun onAction(action: KeyboardAction): Boolean {
        if (action !is SymbolAction) return false

        return when (action) {
            is SymbolAction.SwitchCategory -> {
                currentCategoryIndex = action.index
                true
            }

            is SymbolAction.InputSymbol -> {
                inputConnection?.commitText(action.symbol, 1)
                true
            }
        }
    }
}