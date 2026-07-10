package com.crest247.flickarraykeyboard.core

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.engine.SystemProcessor
import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.DownTriggerable
import com.crest247.flickarraykeyboard.core.models.Flickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.KeyboardKeyEvent
import com.crest247.flickarraykeyboard.core.models.LongPressable
import com.crest247.flickarraykeyboard.core.models.Repeatable
import com.crest247.flickarraykeyboard.core.models.UpTriggerable
import com.crest247.flickarraykeyboard.core.models.resolveAction

interface InputProcessor {
    fun onAction(action: KeyboardAction): KeyboardAction?
    fun updateConnection(inputConnection: InputConnection, editorInfo: EditorInfo) {}
}

fun InputProcessor.executeDefault(keyEvent: KeyboardKeyEvent, systemProcessor: SystemProcessor) {
    val baseAction = keyEvent.resolveAction() ?: return

    when (keyEvent) {
        is KeyboardKeyEvent.Down -> (baseAction as? DownTriggerable)?.downAction
        is KeyboardKeyEvent.Click -> (baseAction as? Clickable)?.clickAction
        is KeyboardKeyEvent.Flick -> (baseAction as? Flickable)?.flickAction
        is KeyboardKeyEvent.LongPress -> (baseAction as? LongPressable)?.longPressAction
        is KeyboardKeyEvent.Repeat -> (baseAction as? Repeatable)?.repeatAction
        is KeyboardKeyEvent.Up -> (baseAction as? UpTriggerable)?.upAction
    }?.let { action ->
        this.onAction(action)?.let { returnAction ->
            (returnAction as? SystemAction)?.let {
                systemProcessor.onAction(returnAction)
            }
        }
    }
}