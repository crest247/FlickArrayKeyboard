package com.crest247.flickarraykeyboard.core.engine

import android.view.inputmethod.EditorInfo
import com.crest247.flickarraykeyboard.core.InputProcessor
import com.crest247.flickarraykeyboard.core.KeyboardState
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.KeyData

sealed interface SystemAction {
    object Backspace : SystemAction
    object Space : SystemAction
    object Enter : SystemAction

    data class SwitchModule(val moduleId: Int) : SystemAction
}

class SystemProcessor(
    private val state: KeyboardState
) : InputProcessor<SystemAction> {

    fun handleUniversalKey(keyData: KeyData, direction: Int? = null) {
        when (keyData) {
            is FuncKeyData -> when (keyData.type) {
                FuncType.BACKSPACE -> onAction(SystemAction.Backspace)
                FuncType.SPACE -> onAction(SystemAction.Space)
                FuncType.ENTER -> onAction(SystemAction.Enter)
                else -> {}
            }

            is FlickKeyData<*> -> {
                keyData.directionActions[direction].let {
                    if (it is SystemAction) onAction(it)
                }
            }

            else -> {}
        }
    }

    override fun onAction(action: SystemAction) {
        val inputConnection = state.currentInputConnection
        val editorInfo = state.currentEditorInfo

        when (action) {
            is SystemAction.Backspace -> {
                inputConnection?.deleteSurroundingText(1, 0)
            }

            is SystemAction.Space -> {
                inputConnection?.commitText(" ", 1)
            }

            is SystemAction.Enter -> {
                val actionId = editorInfo?.imeOptions?.and(EditorInfo.IME_MASK_ACTION)
                if (actionId == EditorInfo.IME_ACTION_NONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    inputConnection?.commitText("\n", 1)
                } else {
                    actionId?.let { inputConnection?.performEditorAction(it) }
                }
            }

            is SystemAction.SwitchModule -> {
                state.switchModule(state.availableModules[action.moduleId])
            }
        }
    }
}