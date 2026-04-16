package com.crest247.flickarraykeyboard.core

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection

interface InputProcessor<T> {
    fun onAction(action: T)
    fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {}
}

