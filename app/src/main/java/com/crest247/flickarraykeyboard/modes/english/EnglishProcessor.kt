package com.crest247.flickarraykeyboard.modes.english

import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.crest247.flickarraykeyboard.core.InputProcessor

class EnglishProcessor : InputProcessor<EnglishAction> {
    private var inputConnection: InputConnection? = null
    var editorInfo: EditorInfo? = null
        private set
    var shiftState by mutableStateOf(ShiftState.LOWERCASE)
        private set
    var variant by mutableStateOf(EnglishVariant.Default)
    fun updateConnection(ic: InputConnection, info: EditorInfo) {
        this.inputConnection = ic
        this.editorInfo = info
    }

    override fun onAction(action: EnglishAction) {
        when (action) {
            is EnglishAction.InputChar -> {
                inputConnection?.commitText(action.char, 1)

                if (shiftState == ShiftState.UPPERCASE) {
                    shiftState = ShiftState.LOWERCASE
                }
            }

            is EnglishAction.ToggleShift -> {
                shiftState = when (shiftState) {
                    ShiftState.LOWERCASE -> ShiftState.UPPERCASE
                    ShiftState.UPPERCASE -> ShiftState.CAPS_LOCK
                    ShiftState.CAPS_LOCK -> ShiftState.LOWERCASE
                }
            }
        }
    }
}