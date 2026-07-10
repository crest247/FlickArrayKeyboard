package com.crest247.flickarraykeyboard.modes.number

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.models.KeyboardAction

class NumberProcessor : InputProcessor {
    private var inputConnection: InputConnection? = null
    var editorInfo: EditorInfo? = null; private set

    override fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {
        this.inputConnection = inputConnection
        this.editorInfo = editorInfo
    }

    override fun onAction(action: KeyboardAction): KeyboardAction? {
        if (action !is NumberAction) return action

        when (action) {
            is NumberAction.InputChar ->
                inputConnection?.commitText(action.char, 1)
        }
        return null
    }
}