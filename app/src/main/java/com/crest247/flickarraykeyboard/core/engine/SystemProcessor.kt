package com.crest247.flickarraykeyboard.core.engine

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.KeyboardState
import com.crest247.flickarraykeyboard.core.extension.sendDownUpKeyEvents
import com.crest247.flickarraykeyboard.core.models.Clickable
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.models.Repeatable

sealed interface SystemAction : KeyboardAction {
    object Backspace : SystemAction, Clickable, Repeatable
    object Space : SystemAction, Clickable
    object Enter : SystemAction, Clickable
    object Tab : SystemAction, Clickable
    data class SwitchModule(val moduleId: Int) : SystemAction, Clickable
}

class SystemProcessor(
    private val state: KeyboardState
) : InputProcessor {

    override fun onAction(action: KeyboardAction): KeyboardAction? {
        if (action !is SystemAction) return action

        val inputConnection = state.currentInputConnection
        val editorInfo = state.currentEditorInfo

        when (action) {
            is SystemAction.Backspace ->
                inputConnection?.sendDownUpKeyEvents(KeyEvent.KEYCODE_DEL)

            is SystemAction.Space ->
                inputConnection?.sendDownUpKeyEvents(KeyEvent.KEYCODE_SPACE)

            is SystemAction.Tab ->
                inputConnection?.sendDownUpKeyEvents(KeyEvent.KEYCODE_TAB)

            is SystemAction.Enter -> {
                val options = editorInfo?.imeOptions ?: 0
                val noEnterAction = (options and EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0
                val actionId =
                    if (noEnterAction) EditorInfo.IME_ACTION_NONE else options and EditorInfo.IME_MASK_ACTION
                if (actionId != EditorInfo.IME_ACTION_NONE)
                    inputConnection?.performEditorAction(actionId)
                else
                    inputConnection?.commitText("\n", 1)
            }

            is SystemAction.SwitchModule ->
                state.switchModule(state.availableModules[action.moduleId])
        }
        return null
    }
}