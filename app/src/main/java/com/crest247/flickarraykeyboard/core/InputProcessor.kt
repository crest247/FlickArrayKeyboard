package com.crest247.flickarraykeyboard.core

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection

interface KeyboardAction
interface RepeatableAction : KeyboardAction
interface TriggerOnDownAction : KeyboardAction

interface InputProcessor {
    fun onAction(action: KeyboardAction): Boolean
    fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {}
}

