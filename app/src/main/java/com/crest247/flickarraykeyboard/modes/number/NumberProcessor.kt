package com.crest247.flickarraykeyboard.modes.number

import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.KeyboardAction
import com.crest247.flickarraykeyboard.modes.english.EnglishAction

class NumberProcessor : InputProcessor {
    private var inputConnection: InputConnection? = null
    var editorInfo: EditorInfo? = null; private set

    override fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {
        this.inputConnection = inputConnection
        this.editorInfo = editorInfo
    }

    override fun onAction(action: KeyboardAction): Boolean {
        return if (action !is NumberAction) false
        else when (action) {
            is NumberAction.InputChar -> {
                inputConnection?.commitText(action.char, 1)
                true
            }
        }
    }
}