package com.crest247.flickarraykeyboard.core.engine

import android.view.inputmethod.EditorInfo
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.KeyboardState

sealed interface SystemAction {
    object Backspace : SystemAction
    object Enter : SystemAction
    object SwitchLanguage : SystemAction
}

class SystemProcessor(
    private val state: KeyboardState
) : InputProcessor<SystemAction> {

    override fun onAction(action: SystemAction) {
        val inputConnection = state.currentInputConnection
        val editorInfo = state.currentEditorInfo

        when (action) {
            is SystemAction.Backspace -> {
                inputConnection?.deleteSurroundingText(1, 0)
            }

            is SystemAction.Enter -> {
                val actionId = editorInfo?.imeOptions?.and(EditorInfo.IME_MASK_ACTION)
                if (actionId == EditorInfo.IME_ACTION_NONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    inputConnection?.commitText("\n", 1)
                } else {
                    actionId?.let { inputConnection?.performEditorAction(it) }
                }
            }

            is SystemAction.SwitchLanguage -> {}
        }
    }
}