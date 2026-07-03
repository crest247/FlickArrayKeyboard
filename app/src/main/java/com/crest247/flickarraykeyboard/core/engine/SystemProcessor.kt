package com.crest247.flickarraykeyboard.core.engine

import android.view.inputmethod.EditorInfo
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.KeyboardAction
import com.crest247.flickarraykeyboard.core.KeyboardState
import com.crest247.flickarraykeyboard.core.RepeatableAction

sealed interface SystemAction : KeyboardAction {
    object Backspace : SystemAction, RepeatableAction
    object Space : SystemAction
    object Enter : SystemAction

    data class SwitchModule(val moduleId: Int) : SystemAction
}

class SystemProcessor(
    private val state: KeyboardState
) : InputProcessor {

    override fun onAction(action: KeyboardAction): Boolean {
        if (action !is SystemAction) return false

        val inputConnection = state.currentInputConnection
        val editorInfo = state.currentEditorInfo

        return when (action) {
            is SystemAction.Backspace -> {
                inputConnection?.deleteSurroundingText(1, 0)
                true
            }

            is SystemAction.Space -> {
                inputConnection?.commitText(" ", 1)
                true
            }

            is SystemAction.Enter -> {
                val actionId = editorInfo?.imeOptions?.and(EditorInfo.IME_MASK_ACTION)
                if (actionId == EditorInfo.IME_ACTION_NONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    inputConnection?.commitText("\n", 1)
                } else {
                    actionId?.let { inputConnection?.performEditorAction(it) }
                }
                true
            }

            is SystemAction.SwitchModule -> {
                state.switchModule(state.availableModules[action.moduleId])
                true
            }
        }
    }
}